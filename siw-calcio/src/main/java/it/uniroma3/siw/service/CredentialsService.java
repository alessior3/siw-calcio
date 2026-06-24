package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {
	private CredentialsRepository credentialsRepository;
	private PasswordEncoder passwordEncoder;

	public CredentialsService(CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder) {
		this.credentialsRepository = credentialsRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}

	@Transactional
	public Credentials save(Credentials credentials) {
		credentials.setRuoloUtente(it.uniroma3.siw.model.Ruolo.USER);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credentialsRepository.save(credentials);
	}
}
