package br.com.victor;

public class Dado {
	
	private Character caracter;
	private Integer frequencia;

	public Dado(Character caracter, Integer frequencia) {
		this.caracter = caracter;
		this.frequencia = frequencia;
	}

	@Override
	public String toString() {
		return "[" + (caracter == null ? "null" : frequencia) + "]: " + frequencia;
	}

	@Override
	public int hashCode() {
		int result = caracter != null ? caracter.hashCode() : 0;
		result = 31 * result + frequencia;
		return result;
	}

	public Character getCaracter() {
		return caracter;
	}

	public void setCaracter(Character caracter) {
		this.caracter = caracter;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}
	
	
	
}
