package tn.esprit.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Dislike;
import tn.esprit.spring.entities.likes;
@Repository
public interface DislikeRepo extends CrudRepository  <Dislike, Integer> {

	
	@Query(value ="SELECT posts_id_post FROM dislike WHERE date_dislike BETWEEN (CURRENT_DATE() - INTERVAL 1 MONTH) AND CURRENT_DATE()GROUP BY posts_id_post ORDER BY COUNT(dislike.id) DESC LIMIT 1", nativeQuery = true)
	public int getPostsWithMostLikes();
	@Query(value ="SELECT * FROM likes GROUP BY posts_id_post ORDER BY COUNT(likes.id) DESC ", nativeQuery = true)
	public Set<likes> getListPostsWithMostLikes();
}
