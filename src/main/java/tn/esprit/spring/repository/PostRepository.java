package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Post;

@Repository
public interface PostRepository extends CrudRepository <Post, Integer> {


	@Query(value = "SELECT posts_id_post FROM likes WHERE date_dislike BETWEEN (date_dislike - INTERVAL 1 MONTH) AND CURRENT_DATE() GROUP BY posts_id_post ORDER BY COUNT(likes.id) DESC LIMIT 1", nativeQuery = true)
	public int getEmployeWithMostPosts();
	@Query(value = "SELECT employees_id_employee FROM post GROUP BY employees_id_employee ORDER BY COUNT(id_post) DESC ", nativeQuery = true)
	public int getListEmployeWithMostPosts();
	
}
