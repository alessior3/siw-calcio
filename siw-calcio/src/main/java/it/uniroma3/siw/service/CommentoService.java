package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.exception.CommentoNotFoundException;
import it.uniroma3.siw.exception.UtenteNotAutoreCommentoException;

@Service
@Transactional(readOnly = true)
public class CommentoService {

	private CommentoRepository commentoRepository;

	public CommentoService(CommentoRepository commentoRepository) {
		this.commentoRepository = commentoRepository;
	}

	public Commento findById(Long id) throws CommentoNotFoundException {
		Optional<Commento> optionalCommento = commentoRepository.findById(id);
		if (optionalCommento.isPresent()) {
			return optionalCommento.get();
		}
		throw new CommentoNotFoundException(id);
	}

	public Iterable<Commento> findAll() {
		return commentoRepository.findAll();
	}

	@Transactional
	public void save(Commento commento) {
		this.commentoRepository.save(commento);
	}
	
	@Transactional
	public void update(Commento commentoModificato, String username) throws UtenteNotAutoreCommentoException{
		// 1. Recupero il commento dal database (con tutti i campi non nulli)
		Commento commentoOriginale = this.findById(commentoModificato.getId());
		
		// 2. Faccio il controllo di sicurezza sull'autore originale
		if (!commentoOriginale.getUtente().getCredentials().getUsername().equals(username)) {
			throw new UtenteNotAutoreCommentoException();
		}
		
		// 3. Modifico il testo e aggiorno lo stato del commento
		commentoOriginale.setTesto(commentoModificato.getTesto());
		commentoOriginale.setDataOra(java.time.LocalDateTime.now());
		commentoOriginale.setModificato(true);
		
		// 4. Salvo il commento aggiornato
		this.commentoRepository.save(commentoOriginale);
	}
	
}