package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.entities.Reaction;
@Repository
public interface ReactionRepository extends CrudRepository <Reaction, Integer>{
	@Query(value="SELECT evenements_id_evenement FROM `reaction` WHERE date_feedback BETWEEN (CURRENT_DATE() - INTERVAL 1 MONTH) AND CURRENT_DATE() GROUP BY note DESC LIMIT 1 \r\n", nativeQuery = true)
	public int getPostsWithMostLikes();
			

}
