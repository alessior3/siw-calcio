package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Arbitro;
import it.uniroma3.siw.repository.ArbitroRepository;

@Service
@Transactional(readOnly = true)
public class ArbitroService {
	private ArbitroRepository arbitroRepository;
	
	public ArbitroService(ArbitroRepository arbitroRepository) {
		this.arbitroRepository=arbitroRepository;
	}
		
	public Optional<Arbitro> findById(Long id) {
		return arbitroRepository.findById(id);
	}
	
	public Iterable<Arbitro> findAll(){
		return arbitroRepository.findAll();
	}
}
