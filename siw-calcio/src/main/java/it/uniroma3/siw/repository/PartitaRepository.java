package it.uniroma3.siw.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Partita;
import it.uniroma3.siw.model.Squadra;

public interface PartitaRepository extends CrudRepository <Partita, Long>{
	@Query("SELECT p FROM Partita p LEFT JOIN FETCH p.commenti WHERE p.id = :id")
	Optional<Partita> findByIdWithCommenti(@Param("id") Long idTorneoDaCercare);

	@Override
	@EntityGraph(attributePaths = {"squadraCasa", "squadraTrasferta", "torneo"})
	Iterable<Partita> findAll();
	
	public boolean existsByDataOraAndSquadraCasaAndSquadraTrasferta(LocalDateTime dataOra, Squadra squadraCasa, Squadra squadraTrasferta);
}
