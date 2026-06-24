package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"nome", "anno"}))
public class Torneo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Min(1850)
	@Column(nullable = false)
	private Long anno;
	
	@Column(length = 2000)
	private String descrizione;
	
	@ManyToMany
	private List<Squadra> squadre;
	
	@OneToMany(mappedBy = "torneo", cascade = CascadeType.REMOVE)
	private List<Partita> partite;
	
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

	public Long getAnno() {
		return anno;
	}

	public void setAnno(Long anno) {
		this.anno = anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public List<Squadra> getSquadra() {
		return squadre;
	}

	public void setSquadra(List<Squadra> squadre) {
		this.squadre = squadre;
	}

	public List<Partita> getPartita() {
		return partite;
	}

	public void setPartita(List<Partita> partite) {
		this.partite = partite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torneo other = (Torneo) obj;
		return Objects.equals(anno, other.anno) && Objects.equals(nome, other.nome);
	}

}