package it.uniroma3.siw.exception;

public class UtenteNotFoundException extends RuntimeException{
	public UtenteNotFoundException(Long id) {
		super("L'utente con ID: " + id + " non esiste");
	}
}
