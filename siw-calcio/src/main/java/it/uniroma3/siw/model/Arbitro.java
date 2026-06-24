package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Arbitro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String cognome;
	
	@NotNull
	@Column(nullable = false, unique = true)
	private Long codiceArbitrale;
	
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getNome() {
		return this.nome;
	}
	

	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	
	public Long getCodiceArbitrale() {
		return this.codiceArbitrale;
	}
	
	public void setCodiceArbitrale(Long codiceArbitrale) {
		this.codiceArbitrale=codiceArbitrale;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codiceArbitrale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return Objects.equals(codiceArbitrale, other.codiceArbitrale);
	}
}
