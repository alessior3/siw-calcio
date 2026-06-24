package it.uniroma3.siw.exception;

public class SquadraNotFoundException extends RuntimeException{
	public SquadraNotFoundException(Long id) {
		super("La squadra con ID: " + id + "non esiste");
	}
}
