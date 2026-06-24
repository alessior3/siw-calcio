package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import it.uniroma3.siw.service.ArbitroService;

@Controller
public class ArbitroController {
	private ArbitroService arbitroService;

	public ArbitroController(ArbitroService arbitroService) {
		this.arbitroService = arbitroService;
	}
}
