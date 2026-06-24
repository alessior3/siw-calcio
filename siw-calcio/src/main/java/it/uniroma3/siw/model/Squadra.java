package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"nome", "anno_di_fondazione"}))
public class Squadra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@Min(1850)
	@Max(2026)
	@Column(nullable = false)
	private Long annoDiFondazione;
	
	@NotBlank
	@Column(nullable = false)
	private String citta;
	
	@OneToMany(mappedBy = "squadra")
	private List<Giocatore> giocatori;
	
	@ManyToMany(mappedBy = "squadra")
	private List<Giocatore> squadre;


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

	public Long getAnnoDiFondazione() {
		return annoDiFondazione;
	}

	public void setAnnoDiFondazione(Long annoDiFondazione) {
		this.annoDiFondazione = annoDiFondazione;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annoDiFondazione, citta, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Squadra other = (Squadra) obj;
		return Objects.equals(annoDiFondazione, other.annoDiFondazione) && Objects.equals(citta, other.citta)
				&& Objects.equals(nome, other.nome);
	}
}