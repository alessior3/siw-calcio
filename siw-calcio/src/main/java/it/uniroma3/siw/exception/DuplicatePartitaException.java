package it.uniroma3.siw.exception;

import java.time.LocalDateTime;

import it.uniroma3.siw.model.Squadra;

public class DuplicatePartitaException extends RuntimeException{
	
	public DuplicatePartitaException(LocalDateTime dataOra, Squadra squadraCasa, Squadra squadraTrasferta) {
		super("Una partita tra " + squadraCasa + " e " + squadraTrasferta + " in data " + dataOra + " è già presente nel sistema");
	}
}
