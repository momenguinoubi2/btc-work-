package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Response;

public interface IQuestionService {
	Question addQuestion(Question back,  Integer  emp_id);
	Question modifyQuestion(Question back);
	Question findQuestion(Integer back);
	List<Question> ListQuestions();
	void deleteQuestion(Integer id);
	Set<Question> findQuestionbyQuiz(Integer id);
	Question addquestions(Integer Id,Set<Response> Res);
}
