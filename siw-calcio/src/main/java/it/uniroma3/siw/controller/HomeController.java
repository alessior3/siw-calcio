package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.PartitaService;

@Controller
public class HomeController {
	PartitaService partitaService;
	
	public HomeController(PartitaService partitaService) {
		this.partitaService=partitaService;
	}
	
	@GetMapping("/")
	public String getHome(Model model) {
		return "index.html";
	}

	// TODO: Rimuovere dopo i test!
	@GetMapping("/crash")
	public String crashTest() {
		throw new RuntimeException("Simulazione di errore interno del server (Cartellino Rosso!)");
	}
}
