package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Voyage;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
	@Query(value="select  COUNT('employees_id_employee')  from participation p where p.voyages_id_voyage=:idVoyage ",nativeQuery = true)
	//nbre de participant dans un projet bien determine
		int getNbreIntervenant(@Param("idVoyage") int idVoyage);

}
