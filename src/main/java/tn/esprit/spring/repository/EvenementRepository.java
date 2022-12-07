package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Evenement;
@Repository
public interface EvenementRepository extends CrudRepository <Evenement, Integer>{
	@Query(value="SELECT `id_evenement` FROM evenement e INNER JOIN entreprise p WHERE p.domain=e.domain AND p.id_entreprise=:id_entreprise",nativeQuery = true)
	//employee qui a le nbre de projet le plus grand 
		List<Integer> getEvenementEntrepriseDomain(@Param("id_entreprise") int id_entreprise);
}
