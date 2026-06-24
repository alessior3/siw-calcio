package it.uniroma3.siw.model;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"data_ora", "squadra_casa_id", "squadra_trasferta_id"}))
public class Partita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime dataOra;
	
	@NotBlank
	private String luogo;
	
	@Min(0)
	private Long goalCasa;
	
	@Min(0)
	private Long goalTrasferta;
	
	@Enumerated(EnumType.STRING)
	private Stato statoPartita;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Torneo torneo;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Arbitro arbitro;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Squadra squadraCasa;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Squadra squadraTrasferta;
	
	@OneToMany(mappedBy = "partita", cascade = CascadeType.ALL)
	@OrderBy("dataOra DESC")
	private List<Commento> commenti;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Long getGoalCasa() {
		return goalCasa;
	}

	public void setGoalCasa(Long goalCasa) {
		this.goalCasa = goalCasa;
	}

	public Long getGoalTrasferta() {
		return goalTrasferta;
	}

	public void setGoalTrasferta(Long goalTrasferta) {
		this.goalTrasferta = goalTrasferta;
	}
	
	public Stato getStatoPartita() {
		return this.statoPartita;
	}
	
	public void setStatoPartita(Stato statoPartita) {
		this.statoPartita=statoPartita;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}

	public Squadra getSquadraCasa() {
		return squadraCasa;
	}

	public void setSquadraCasa(Squadra squadraCasa) {
		this.squadraCasa = squadraCasa;
	}

	public Squadra getSquadraTrasferta() {
		return squadraTrasferta;
	}

	public void setSquadraTrasferta(Squadra squadraTrasferta) {
		this.squadraTrasferta = squadraTrasferta;
	}

	public List<Commento> getCommento() {
		return commenti;
	}

	public void setCommento(List<Commento> commenti) {
		this.commenti = commenti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataOra, squadraCasa, squadraTrasferta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita other = (Partita) obj;
		return Objects.equals(dataOra, other.dataOra) && Objects.equals(squadraCasa, other.squadraCasa)
				&& Objects.equals(squadraTrasferta, other.squadraTrasferta);
	}
}