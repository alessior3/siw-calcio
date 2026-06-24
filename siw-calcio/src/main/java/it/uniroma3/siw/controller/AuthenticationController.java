package it.uniroma3.siw.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	private CredentialsService credentialsService;

	public AuthenticationController(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}
	
	@GetMapping("/register")
	public String formRegister(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "register.html";
	}
	
	@GetMapping("/login")
	public String formLogin(Model model) {
		return "login.html";
	}
	
	@GetMapping("/success")
	public String defaultAfterLogin(Model model) {
		return "success.html";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("utente") Utente utente, BindingResult utenteBindingResult, @Valid @ModelAttribute("credentials")
								Credentials credentials, BindingResult credentialsBindingResult, Model model) {
		if(utenteBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
			return "register.html";
		}
		credentials.setUtente(utente);
		credentialsService.save(credentials);
		model.addAttribute("utente", utente);
		return "registrationSuccessful.html";	
	}

}
