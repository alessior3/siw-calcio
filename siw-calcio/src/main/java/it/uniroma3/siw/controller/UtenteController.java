package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import it.uniroma3.siw.service.UtenteService;

@Controller
public class UtenteController {	
	private UtenteService utenteService;
	
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

}
