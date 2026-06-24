package it.uniroma3.siw.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.exception.UtenteNotAutoreCommentoException;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CommentoService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PartitaService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class CommentoController {
	CommentoService commentoService;
	PartitaService partitaService;
	CredentialsService credentialsService;
	GlobalController globalController;

	public CommentoController(CommentoService commentoService, PartitaService partitaService, CredentialsService credentialsService, GlobalController globalController) {
		this.commentoService = commentoService;
		this.partitaService = partitaService;
		this.credentialsService = credentialsService;
		this.globalController = globalController;
	}

	@PostMapping("/user/partite/{idPartita}/commenti")
	public String nuovoCommento(@PathVariable("idPartita") Long idPartita, @Valid @ModelAttribute("commento") Commento commento) {
//		SPOSTARE TUTTI I SETTER NEL SERVICE!!!!!!!!!! coglione!
		Partita partita = partitaService.findById(idPartita); // recuperiamo la partita
		String username = globalController.getUser().getUsername();  
		Utente utente = credentialsService.getCredentials(username).getUtente(); //recuperiamo l'utente tramite il suo username
		commento.setPartita(partita); 
		commento.setDataOra(LocalDateTime.now()); // impostiamo data e ora attuale
		commento.setUtente(utente); //associamo al commento il suo utente
		commentoService.save(commento);
		return "redirect:/partite/" + idPartita;
	}

	@PostMapping("/user/commenti/{id}/edit")
	public String modificaCommento(@PathVariable("id") Long id, @Valid @ModelAttribute("commento") Commento commento, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			// Ricarica la partita originale per evitare NullPointerException nel template (tasto annulla)
			Commento originale = commentoService.findById(id);
			commento.setPartita(originale.getPartita());
			return "commento/edit";
		}
		try {
			this.commentoService.update(commento, globalController.getUser().getUsername());
			// Recupera il commento aggiornato per capire l'ID della partita a cui fare il redirect
			Commento salvato = commentoService.findById(commento.getId());
			return "redirect:/partite/" + salvato.getPartita().getId();
		}
		catch(UtenteNotAutoreCommentoException e) {
			return "error/403";
		}
	}

	@GetMapping("/user/commenti/{id}/edit")
	public String formModificaCommento(@PathVariable("id") Long id, Model model) {
		try {
			Commento commento = commentoService.findById(id);
			String usernameLoggato = globalController.getUser().getUsername();

			// Se non sei l'autore originale, ti blocco e lancio l'eccezione
			if (!commento.getUtente().getCredentials().getUsername().equals(usernameLoggato)) {
				throw new UtenteNotAutoreCommentoException();
			}

			model.addAttribute("commento", commento);
			return "commento/edit";

		} catch (UtenteNotAutoreCommentoException e) {
			return "error/403";
		}
	}
}
