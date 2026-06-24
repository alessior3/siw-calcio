package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import it.uniroma3.siw.service.GiocatoreService;

@Controller
public class GiocatoreController {
	GiocatoreService giocatoreService;

	public GiocatoreController(GiocatoreService giocatoreService) {
		this.giocatoreService = giocatoreService;
	}
}
