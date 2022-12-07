package tn.esprit.spring.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Badge;
import tn.esprit.spring.entities.Difficulty;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Result;
import tn.esprit.spring.entities.Type;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
	@Query("SELECT COUNT(c) FROM Result c WHERE c.quiz.TypeQuiz= :Typeq AND c.employee.idEmployee = :emi AND c.badge= :bad")
	Integer Listresultwithtype(@Param("emi") Integer Id,@Param("Typeq") Type type,@Param("bad") Badge badge);
	@Query("SELECT c.employee FROM Result c WHERE c.creationdate= :datef AND c.badge= :bad")
	 Employee employeofthmonth (@Param("datef") Date md,@Param("bad") Badge badge);
	
}
