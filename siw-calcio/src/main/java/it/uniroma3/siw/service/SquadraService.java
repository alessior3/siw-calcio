package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.exception.DuplicateSquadraException;
import it.uniroma3.siw.exception.SquadraNotFoundException;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;

@Service
@Transactional(readOnly = true)
public class SquadraService {
	private SquadraRepository squadraRepository;
	
	public SquadraService(SquadraRepository squadraRepository) {
		this.squadraRepository = squadraRepository;
	}
	
	public Squadra findById(Long id) throws SquadraNotFoundException{
		Optional<Squadra> optionalSquadra = squadraRepository.findById(id);
		if(optionalSquadra.isPresent()) {
			return optionalSquadra.get();
		}
		throw new SquadraNotFoundException(id);
	}
	
	public Squadra findByIdWithGiocatori(Long id) throws SquadraNotFoundException{
		Optional<Squadra> optionalSquadra=squadraRepository.findByIdWithGiocatori(id);
		if(optionalSquadra.isPresent()) {
			return optionalSquadra.get();
		}
		throw new SquadraNotFoundException(id);
	}
	
	public Iterable<Squadra> findAll(){
		return squadraRepository.findAll();
	}
	
	@Transactional
	public void save(Squadra squadra) throws DuplicateSquadraException{
		if(squadraRepository.existsByNomeAndAnnoDiFondazione(squadra.getNome(), squadra.getAnnoDiFondazione())) {
			throw new DuplicateSquadraException(squadra.getNome(), squadra.getAnnoDiFondazione());
		}
		this.squadraRepository.save(squadra);
	}
	
	@Transactional
	public void update(Squadra squadraNuova) throws DuplicateSquadraException {
		Squadra squadraOriginale = this.findById(squadraNuova.getId());
		
		// Se l'admin ha cambiato il nome o l'anno, controlliamo che non esista già un'altra squadra uguale
		if (!squadraOriginale.getNome().equals(squadraNuova.getNome()) || 
		    !squadraOriginale.getAnnoDiFondazione().equals(squadraNuova.getAnnoDiFondazione())) {
			if(squadraRepository.existsByNomeAndAnnoDiFondazione(squadraNuova.getNome(), squadraNuova.getAnnoDiFondazione())) {
				throw new DuplicateSquadraException(squadraNuova.getNome(), squadraNuova.getAnnoDiFondazione());
			}
		}
		
		squadraOriginale.setAnnoDiFondazione(squadraNuova.getAnnoDiFondazione());
		squadraOriginale.setCitta(squadraNuova.getCitta());
		squadraOriginale.setNome(squadraNuova.getNome());
		this.squadraRepository.save(squadraOriginale);
	}
}