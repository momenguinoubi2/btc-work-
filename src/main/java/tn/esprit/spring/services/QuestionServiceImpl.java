package tn.esprit.spring.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Complain;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.QuizRepository;
import tn.esprit.spring.repository.ResponseRepository;

@Service
@Slf4j
public class QuestionServiceImpl implements IQuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	ResponseRepository responseRepository;
	
	@Override
	public Question addQuestion(Question back, Integer quiz_id) {
		// TODO Auto-generated method stub
		
		back.setQuizs(quizRepository.findById(quiz_id).get());
		questionRepository.save(back);
		return back;
	}

	@Override
	public Question modifyQuestion(Question back) {
		// TODO Auto-generated method stub
		questionRepository.save(back);
		return back;
	}

	@Override
	public Question findQuestion(Integer back) {
		// TODO Auto-generated method stub
		return questionRepository.findById(back).get();
	}

	@Override
	public List<Question> ListQuestions() {
		// TODO Auto-generated method stub
		 List<Question> lco = (List<Question>) questionRepository.findAll();
		 for (Question question : lco) {
			log.info("this is the body: "+question.getQuestiontext());
		}
		 return lco;
	}

	@Override
	public void deleteQuestion(Integer id) {
		// TODO Auto-generated method stub
		questionRepository.deleteById(id);
	}

	@Override
	public Set<Question> findQuestionbyQuiz(Integer id) {
		// TODO Auto-generated method stub
		Quiz q=quizRepository.findById(id).get();
		
		 for (Question question : q.getQuestions()) {
			log.info("this is the body: "+question.getQuestiontext());
		}
		 return q.getQuestions();
	}

	@Override
	public Question addquestions(Integer Id, Set<Response> Res) {
	Question q= questionRepository.findById(Id).get();
	for (Response response : Res) {
		response.setQuestions(q);
		responseRepository.save(response);
	}			
	questionRepository.save(q);
	return q;
		
		
	}

}
