package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Giocatore;
import it.uniroma3.siw.repository.GiocatoreRepository;

@Service
@Transactional(readOnly = true)
public class GiocatoreService {
	private GiocatoreRepository giocatoreRepository;
	
	public GiocatoreService(GiocatoreRepository giocatoreRepository) {
		this.giocatoreRepository = giocatoreRepository;
	}
	
	public Optional<Giocatore> findById(Long id) {
		return giocatoreRepository.findById(id);
	}
	
	public Iterable<Giocatore> findAll(){
		return giocatoreRepository.findAll();
	}
}