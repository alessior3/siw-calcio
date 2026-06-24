package it.uniroma3.siw.exception;

public class DuplicateTorneoException extends RuntimeException{
	
	public DuplicateTorneoException(String nome, Long anno) {
		super("Il torneo " + nome + " del " + anno + "è già presente nel sistema");
	}
}
