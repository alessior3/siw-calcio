package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;
import it.uniroma3.siw.exception.UtenteNotFoundException;

@Service
@Transactional(readOnly = true)
public class UtenteService {
	private UtenteRepository utenteRepository;
	
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}
	
	public Utente findById(Long id) throws UtenteNotFoundException {
		Optional<Utente> optionalUtente = utenteRepository.findById(id);
		if (optionalUtente.isPresent()) {
			return optionalUtente.get();
		}
		throw new UtenteNotFoundException(id);
	}
	
	public Iterable<Utente> findAll(){
		return utenteRepository.findAll();
	}
	
	@Transactional
	public void save(Utente utente) {
		this.utenteRepository.save(utente);
	}
}