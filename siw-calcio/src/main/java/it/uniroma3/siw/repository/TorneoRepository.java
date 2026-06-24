package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Torneo;

public interface TorneoRepository extends CrudRepository<Torneo, Long>{
	@Query("SELECT t FROM Torneo t LEFT JOIN FETCH t.squadre WHERE t.id = :id")
	Optional<Torneo> findByIdWithSquadre(@Param("id") Long idTorneoDaCercare);

	public boolean existsByNomeAndAnno(String nome, Long anno);
}
