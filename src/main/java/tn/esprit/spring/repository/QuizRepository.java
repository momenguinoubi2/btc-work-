package tn.esprit.spring.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Badge;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Quiz;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> {
	//@Query("SELECT c.employee FROM Result c WHERE c.creationdate= :datef AND c.badge= :bad HAVING MAX(c.score)")
	 //Employee employeofthmonth (@Param("datef") Date md,@Param("bad") Badge badge);
}
