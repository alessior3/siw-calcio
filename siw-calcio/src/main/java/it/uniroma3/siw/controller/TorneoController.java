package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.exception.DuplicateTorneoException;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.service.TorneoService;
import jakarta.validation.Valid;

@Controller
public class TorneoController {

	private TorneoService torneoService;

	public TorneoController(TorneoService torneoService) {
		this.torneoService = torneoService;
	}

	@GetMapping("/tornei/{id}")
	public String showDettaglio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("torneo", this.torneoService.findByIdWithSquadre(id));
		return "torneo/dettaglio.html";
	}

	@GetMapping("/tornei")
	public String showTornei(Model model) {
		model.addAttribute("tornei", this.torneoService.findAll());
		return "torneo/list.html";
	}

	// mostra la form vuota
	@GetMapping("/admin/tornei/new")
	public String formNewTorneo(Model model) {
		model.addAttribute("torneo", new Torneo());
		return "torneo/form.html";
	}

	// riceve i dati, salva e fa ridirect
	@PostMapping("/admin/tornei")
	public String newTorneo(@Valid @ModelAttribute("torneo") Torneo torneo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "torneo/form.html";
		}
		try {
			this.torneoService.save(torneo);
			return "redirect:/tornei";
		}
		catch(DuplicateTorneoException e){
			bindingResult.reject("torneo.duplicate");
			return "torneo/form.html";
		}
	}
}
