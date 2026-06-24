package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"nome", "cognome", "data_di_nascita"}))
public class Giocatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String cognome;
	
	@Past
	@NotNull
	@Column(nullable = false)
	private LocalDate dataDiNascita;
	
	private String ruolo;
	
	@NotNull
	@Min(140)
	@Max(230)
	private Long altezza;
	
	@NotNull
	@ManyToOne
	private Squadra squadra;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Long getAltezza() {
		return altezza;
	}

	public void setAltezza(Long altezza) {
		this.altezza = altezza;
	}
	
	public Squadra getSquadra() {
		return this.squadra;
	}
	
	public void setSquadra(Squadra squadra) {
		this.squadra=squadra;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataDiNascita, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataDiNascita, other.dataDiNascita)
				&& Objects.equals(nome, other.nome);
	}
}