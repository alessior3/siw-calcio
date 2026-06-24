package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.exception.DuplicateTorneoException;
import it.uniroma3.siw.exception.TorneoNotFoundException;
import it.uniroma3.siw.model.Torneo;
import it.uniroma3.siw.repository.TorneoRepository;

@Service
@Transactional(readOnly = true)
public class TorneoService {
	private TorneoRepository torneoRepository;
	
	public TorneoService(TorneoRepository torneoRepository) {
		this.torneoRepository = torneoRepository;
	}
	
	public Torneo findById(Long id) throws TorneoNotFoundException{
		Optional<Torneo> optionalTorneo = torneoRepository.findById(id);
		if(optionalTorneo.isPresent()) {
			return optionalTorneo.get();
		}
		throw new TorneoNotFoundException(id);
	}
	
	public Torneo findByIdWithSquadre(Long id){
		Optional<Torneo> optionalTorneo = torneoRepository.findByIdWithSquadre(id);
		if(optionalTorneo.isPresent()) {
			return optionalTorneo.get();
		}
		throw new TorneoNotFoundException(id);
	}
	
	public Iterable<Torneo> findAll(){
		return torneoRepository.findAll();
	}
	
	@Transactional
	public void save(Torneo torneo) throws DuplicateTorneoException{
		if(torneoRepository.existsByNomeAndAnno(torneo.getNome(), torneo.getAnno())) {
			throw new DuplicateTorneoException(torneo.getNome(), torneo.getAnno());
		}
		this.torneoRepository.save(torneo);
	}
}