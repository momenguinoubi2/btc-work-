package tn.esprit.spring.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.services.IQuestionService;

@RestController
@Api(tags = "management of Questions")
@RequestMapping("/Question")
@Slf4j
public class QuestionContoller {

	
	@Autowired
	IQuestionService questionService;
	
	@PostMapping("/addQuestion/{emp_id}")
	public Question addQuestion(@RequestBody  Question q, @PathVariable("emp_id") Integer id)
	{
		questionService.addQuestion(q, id);
		return q;
	}
	
	@GetMapping("/getallQuestions")
	public List<Question> allQuestions()
	{
		return questionService.ListQuestions();
		
	}
	
	@PutMapping("/modifyQuestion/{comp_id}")
	public Question modifyQuestion(@RequestBody Question q, @PathVariable("comp_id") Integer id)
	{
		return questionService.modifyQuestion(q);
		
	}
	@DeleteMapping("/deleteQuestion/{comp_id}")
	public void modifyQuestion(@PathVariable("comp_id") Integer id)
	{
		questionService.deleteQuestion(id);
		log.info("delete with success");
		
	}
	@GetMapping("/getquestionbyQuiz")
	public Set<Question> getQuestionByQuiz(Integer id)
	{
		return questionService.findQuestionbyQuiz(id);
		
	}
	@PostMapping("/addResponses/{comp_id}")
	public Question setallqustion( @PathVariable("comp_id") Integer id,@RequestBody Set<Response> responses )
	{
		return questionService.addquestions(id,responses);
		
	}
}
