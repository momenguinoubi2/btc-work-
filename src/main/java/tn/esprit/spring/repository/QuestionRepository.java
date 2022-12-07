package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Badge;
import tn.esprit.spring.entities.Difficulty;
import tn.esprit.spring.entities.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
	@Query("SELECT c FROM Question c WHERE c.quizs.idQuiz= :id and c.diffculyQuestion= :diff")
	List<Question> retrieveClientsByDifficulty(@Param("id") Integer Id,@Param("diff") Difficulty difficulty);

}
