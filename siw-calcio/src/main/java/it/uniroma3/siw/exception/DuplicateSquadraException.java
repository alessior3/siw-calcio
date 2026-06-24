package it.uniroma3.siw.exception;

public class DuplicateSquadraException extends RuntimeException{
	
	public DuplicateSquadraException(String nome, Long anno) {
		super("La squadra con nome " + nome + " e anno " + anno + " è già presente nel sistema");
	}
}
