package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ParticipationEvenement;

@Repository
public interface ParticipationEvenementRepository extends CrudRepository<ParticipationEvenement, Integer>{
	@Query(value="select  COUNT('employees_id_employee')  from participation_evenement p where p.`evenements_id_evenement`=:idEvenement ",nativeQuery = true)
	//nbre de participant dans un evenement bien determine
		int getNbreIntervenant(@Param("idEvenement") int idEvenement);
}
