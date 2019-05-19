package br.com.victor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Huffman {

	
	//funcao para usar funcao sort do java x<y
	static int sortPonderacao(Dado a, Dado b) {
		return Integer.compare(a.getFrequencia(), b.getFrequencia());
	}

	//funcao recursiva para juntar os dados e chamar a funcar de ordenacao
	static Dado[] mesclarMenor(Dado[] dado) {
		Dado menor = dado[0];
		Dado menorDireita = dado[1];
		//copia o array partir de 1 ate tamanho do array
		Dado[] dadoArray = Arrays.copyOfRange(dado, 1, dado.length);
		dadoArray[0] = new No(menor, menorDireita);
		Arrays.sort(dadoArray, Huffman::sortPonderacao);
		if (dadoArray.length == 1)
			return dadoArray;
		else
			return mesclarMenor(dadoArray);
	}

	//gera string huffman 
	static void gerarHuffmanString(Dado entrada, int[] codigo, Map<Character, String> codigos) {
		if (entrada instanceof No) {
			No no = (No) entrada;
			if (no.getEsquerda() != null) {
				codigo[codigo.length - 1] = 1;
				gerarHuffmanString(no.getEsquerda(), Arrays.copyOf(codigo, codigo.length + 1), codigos);
			}
			if (no.getDireita() != null) {
				codigo[codigo.length - 1] = 0;
				gerarHuffmanString(no.getDireita(), Arrays.copyOf(codigo, codigo.length + 1), codigos);
			}
		} else {
			codigos.put(entrada.getCaracter(), Arrays.stream(codigo).mapToObj(String::valueOf).collect(Collectors.joining("")));
		}
	}

	public static void main(String[] args) {
		String inputString = "VICTORLEANDRO.COM.BR";
		Map<Character, Integer> frequencia = new HashMap<>();
		inputString.chars().forEach(ci -> {
			char c = (char) ci;
			frequencia.put(c, frequencia.getOrDefault(c, 0) + 1);
		});
		List<Dado> frequenciaOrdenada = frequencia.entrySet().stream().map(e -> new Dado(e.getKey(), e.getValue()))
				.sorted(Huffman::sortPonderacao).collect(Collectors.toList());
		No arvore = (No) mesclarMenor(frequenciaOrdenada.toArray(new Dado[0]))[0];
		Map<Character, String> codigoHuffman = new HashMap<>();
		gerarHuffmanString(arvore, new int[1], codigoHuffman);

		String encoded = inputString.chars().mapToObj(i -> (char) i).map(codigoHuffman::get)
				.collect(Collectors.joining(""));

		System.out.println("Codigos huffman: "+codigoHuffman);
		Double tamanhoEncoded =  (double)  (encoded.length() / 8);
		Double tamanhoInput  = (double) inputString.length();
		Double prop = tamanhoEncoded / tamanhoInput ;
		System.out.println("Comparacao strings: "+ inputString.length() + " vs " + encoded.length() / 8 + " = " + prop);
		System.out.println("Encoded: "+encoded);
		String padded = "1" + encoded;
		BigInteger b = new BigInteger(padded, 2);
		System.out.println(b.toString(16));
		String encodedB = b.toString(2).substring(1);
		System.out.println(encodedB);

		Map<String, Character> huffmanDesafazendo = codigoHuffman.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
		String decoded = "";
		String bucket = "";
		for (int index = 0; index < encodedB.length(); index++) {
			bucket += encodedB.charAt(index);
			if (huffmanDesafazendo.containsKey(bucket)) {
				decoded += huffmanDesafazendo.get(bucket);
				bucket = "";
			}
		}
		System.out.println(decoded);
	}

}
