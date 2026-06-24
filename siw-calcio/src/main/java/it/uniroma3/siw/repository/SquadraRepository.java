package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import it.uniroma3.siw.model.Squadra;

public interface SquadraRepository extends CrudRepository<Squadra, Long>{

	@Query("SELECT s FROM Squadra s LEFT JOIN FETCH s.giocatori WHERE s.id = :id")
	Optional<Squadra> findByIdWithGiocatori(@Param("id") Long idSquadraDaCercare);

	@Query("SELECT DISTINCT s FROM Squadra s LEFT JOIN FETCH s.giocatori")
	Iterable<Squadra> findAllWithGiocatori();

	@Query("SELECT DISTINCT s FROM Squadra s LEFT JOIN FETCH s.giocatori WHERE s.id IN :ids")
	Iterable<Squadra> findAllByIdWithGiocatori(@Param("ids") Iterable<Long> ids);

	public boolean existsByNomeAndAnnoDiFondazione(String nome, Long anno);
}
