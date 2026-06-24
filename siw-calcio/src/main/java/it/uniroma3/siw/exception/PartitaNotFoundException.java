package it.uniroma3.siw.exception;

public class PartitaNotFoundException extends RuntimeException{
	
	public PartitaNotFoundException(Long id) {
		super("La partita con ID: " + id + "non esiste");
	}
}
