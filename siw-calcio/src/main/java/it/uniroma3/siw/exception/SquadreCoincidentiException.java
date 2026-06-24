package it.uniroma3.siw.exception;

import it.uniroma3.siw.model.Squadra;

public class SquadreCoincidentiException extends RuntimeException{
	
	public SquadreCoincidentiException(Squadra squadraCasa, Squadra squadraTrasferta) {
		super("Non può esistere un partita tra due squadre uguali: " + squadraCasa + " " +squadraTrasferta);
	}
}
