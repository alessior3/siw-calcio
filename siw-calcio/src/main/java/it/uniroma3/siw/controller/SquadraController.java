package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.exception.DuplicateSquadraException;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.SquadraService;
import jakarta.validation.Valid;

@Controller
public class SquadraController {

	private SquadraService squadraService;

	public SquadraController(SquadraService squadraService) {
		this.squadraService = squadraService;
	}

	@GetMapping("/squadre/{id}")
	public String showDettaglio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("squadra", this.squadraService.findByIdWithGiocatori(id));
		return "squadra/dettaglio.html";
	}

	@GetMapping("/squadre")
	public String showSquadre(Model model) {
		model.addAttribute("squadre", this.squadraService.findAll());
		return "squadra/list.html";
	}

	@GetMapping("/admin/squadre/new")
	public String formNewSquadra(Model model) {
		model.addAttribute("squadra", new Squadra());
		return "squadra/form.html";
	}

	@PostMapping("/admin/squadre")
	public String newSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "squadra/form.html";
		}
		try {
			this.squadraService.save(squadra);
			return "redirect:/squadre";
		}
		catch(DuplicateSquadraException e){
			bindingResult.reject("squadra.duplicate");
			return "squadra/form.html";
		}
	}
	
	@GetMapping("/admin/squadre/{id}/edit")
	public String formModificaSquadra(@PathVariable("id") Long id, Model model) {
		Squadra squadra = squadraService.findById(id);
		model.addAttribute("squadra", squadra);
		return "squadra/edit.html";
	}
	
	@PostMapping("/admin/squadre/{id}/edit")
	public String modificaSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "squadra/edit.html";
		}
		try {
			this.squadraService.update(squadra);
			return "redirect:/squadre/" + squadra.getId();
		} catch (DuplicateSquadraException e) {
			bindingResult.reject("squadra.duplicate");
			return "squadra/edit.html";
		}
	}
}
