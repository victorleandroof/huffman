package br.com.victor;

public class No extends Dado {
 
	private Dado esquerda;
	private Dado direita;

	public No(Dado menorDireita, Dado menorEsquerda) {
		super(null, menorDireita.getFrequencia() + menorEsquerda.getFrequencia());
		esquerda = menorEsquerda;
		direita = menorDireita;
	}

	public Dado getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Dado esquerda) {
		this.esquerda = esquerda;
	}

	public Dado getDireita() {
		return direita;
	}

	public void setDireita(Dado direita) {
		this.direita = direita;
	}
	
}
