package it.uniroma3.siw.service;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.exception.DuplicatePartitaException;
import it.uniroma3.siw.exception.PartitaNotFoundException;
import it.uniroma3.siw.exception.PartitaNotFuturaException;
import it.uniroma3.siw.exception.SquadreCoincidentiException;
import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Stato;
import it.uniroma3.siw.repository.PartitaRepository;

@Service
@Transactional(readOnly = true)
public class PartitaService {
	private PartitaRepository partitaRepository;

	public PartitaService(PartitaRepository partitaRepository) {
		this.partitaRepository = partitaRepository;
	}
	
	public Partita findById (Long id) throws PartitaNotFoundException{
		Optional<Partita> optionalPartita = partitaRepository.findById(id);
		if(optionalPartita.isPresent()) {
			return optionalPartita.get();
		}
		throw new PartitaNotFoundException(id);
	}

	public Partita findByIdWithCommenti(Long id) throws PartitaNotFoundException{
		Optional<Partita> optionalPartita = partitaRepository.findByIdWithCommenti(id);
		if(optionalPartita.isPresent()) {
			return optionalPartita.get();
		}
		throw new PartitaNotFoundException(id);
	}

	public Iterable<Partita> findAll(){
		return partitaRepository.findAll();
	}

	@Transactional
	public void save(Partita partita) throws DuplicatePartitaException, SquadreCoincidentiException{
		if(partitaRepository.existsByDataOraAndSquadraCasaAndSquadraTrasferta(partita.getDataOra(), partita.getSquadraCasa(), partita.getSquadraTrasferta())) {
			throw new DuplicatePartitaException(partita.getDataOra(), partita.getSquadraCasa(), partita.getSquadraTrasferta());
		}
		if(partita.getSquadraCasa().equals(partita.getSquadraTrasferta())) {
			throw new SquadreCoincidentiException(partita.getSquadraCasa(), partita.getSquadraTrasferta());
		}
		this.partitaRepository.save(partita);
	}
	
	@Transactional 
	public void inserisciRisultato(Long id, Long goalCasa, Long goalTrasferta) throws PartitaNotFuturaException{
		Partita partita = this.findById(id);
		if(partita.getDataOra().isAfter(LocalDateTime.now())) {
			throw new PartitaNotFuturaException();
		}
		partita.setGoalCasa(goalCasa);
		partita.setGoalTrasferta(goalTrasferta);
		partita.setStatoPartita(Stato.GIOCATA);
		this.partitaRepository.save(partita);
	}
	
	public Long count() {
		return this.partitaRepository.count();
	}
}