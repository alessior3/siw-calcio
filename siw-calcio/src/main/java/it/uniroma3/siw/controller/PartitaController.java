package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.exception.DuplicatePartitaException;
import it.uniroma3.siw.exception.PartitaNotFuturaException;
import it.uniroma3.siw.exception.SquadreCoincidentiException;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.service.PartitaService;
import jakarta.validation.Valid;

@Controller
public class PartitaController {
	PartitaService partitaService;

	public PartitaController(PartitaService partitaService) {
		this.partitaService = partitaService;
	}

	@GetMapping("/partite/{id}")
	public String showDettaglio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("partita", this.partitaService.findByIdWithCommenti(id));
		model.addAttribute("commento", new Commento());
		return "partita/dettaglio.html";
	}

	@GetMapping("/partite")
	public String showCalendario(Model model) {
		model.addAttribute("partite", this.partitaService.findAll());
		return "partita/calendario.html";
	}

	@GetMapping("/admin/partite/new")
	public String formNewPartita(Model model) {
		model.addAttribute("partita", new Partita());
		return "partita/form.html";
	}

	@PostMapping("/admin/partite")
	public String newPartita(@Valid @ModelAttribute("partita") Partita partita, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "partita/form.html";
		}
		try {
			this.partitaService.save(partita);
			return "redirect:/partite";
		} catch (DuplicatePartitaException e) {
			bindingResult.reject("partita.duplicate");
			return "partita/form.html";
		} catch (SquadreCoincidentiException e) {
			bindingResult.reject("partita.squadreCoincidenti");
			return "partita/form.html";
		}
	}

	@GetMapping("/admin/partite/{id}/inserimento-risultato")
	public String formInserisciRisultato(@PathVariable("id") Long id, Model model) {
		Partita partita = partitaService.findById(id);
		model.addAttribute("partita", partita);
		return "partita/risultato";
	}

	@PostMapping("/admin/partite/{id}/inserimento-risultato")
	public String inserisciRisultato(@PathVariable("id") Long id, @ModelAttribute("partita") Partita partita,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			Partita vera = this.partitaService.findById(id);
			partita.setSquadraCasa(vera.getSquadraCasa());
			partita.setSquadraTrasferta(vera.getSquadraTrasferta());
			return "partita/risultato.html";
		}

		try {
			this.partitaService.inserisciRisultato(id, partita.getGoalCasa(), partita.getGoalTrasferta());
			return "redirect:/partite/" + id;
		} catch (PartitaNotFuturaException e) {
			bindingResult.reject("partita.futura");
			Partita vera = this.partitaService.findById(id);
			partita.setSquadraCasa(vera.getSquadraCasa());
			partita.setSquadraTrasferta(vera.getSquadraTrasferta());
			return "partita/risultato.html";
		}
	}
}
