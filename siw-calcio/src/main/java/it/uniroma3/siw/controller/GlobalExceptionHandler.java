package it.uniroma3.siw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import it.uniroma3.siw.exception.PartitaNotFoundException;
import it.uniroma3.siw.exception.SquadraNotFoundException;
import it.uniroma3.siw.exception.TorneoNotFoundException;
import it.uniroma3.siw.exception.CommentoNotFoundException;
import it.uniroma3.siw.exception.UtenteNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SquadraNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleSquadraNotFound(SquadraNotFoundException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/404.html";
	}
	
	@ExceptionHandler(PartitaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handlePartitaNotFound(PartitaNotFoundException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/404.html";
	}
	
	@ExceptionHandler(TorneoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleTorneoNotFound(TorneoNotFoundException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/404.html";
	}
	
	@ExceptionHandler(CommentoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleCommentoNotFound(CommentoNotFoundException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/404.html";
	}
	
	@ExceptionHandler(UtenteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleUtenteNotFound(UtenteNotFoundException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/404.html";
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public String handleNoResourceFound(NoResourceFoundException e, Model model) {
	    model.addAttribute("errorMessage", "Pagina non trovata");
	    return "error/404";
	    }

	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  public String handleUnexpectedException(Exception e, Model model) {
	    model.addAttribute("errorMessage","Si è verificato un errore interno. Riprovare più tardi.");
	    return "error/500";
	  }
	}

