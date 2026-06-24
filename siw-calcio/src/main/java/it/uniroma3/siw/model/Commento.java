package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Commento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, length = 2000)
	private String testo;
	
	private LocalDateTime dataOra;
	
	@Column(columnDefinition = "boolean default false")
	private boolean modificato;
	
	@ManyToOne
	private Utente utente;
	
	@ManyToOne
	private Partita partita;
	
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getTesto() {
		return this.testo;
	}
	
	public void setTesto(String testo) {
		this.testo=testo;
	}
	
	public LocalDateTime getDataOra() {
		return this.dataOra;
	}
	
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra=dataOra;
	}

	public boolean isModificato() {
		return modificato;
	}

	public void setModificato(boolean modificato) {
		this.modificato = modificato;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Partita getPartita() {
		return partita;
	}

	public void setPartita(Partita partita) {
		this.partita = partita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataOra, testo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commento other = (Commento) obj;
		return Objects.equals(dataOra, other.dataOra) && Objects.equals(testo, other.testo);
	}
}
