package tn.esprit.spring.controller;

import java.util.List;

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
import tn.esprit.spring.entities.FeedBack;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Result;
import tn.esprit.spring.services.IQuizService;

@RestController
@Api(tags = "management of Quizs")
@RequestMapping("/Quiz")
@Slf4j
public class QuizContoller {
	
	@Autowired
	IQuizService quizInterface;
	
	@PostMapping("/addQuiz/{emp_id}")
	public Quiz addQuiz(@RequestBody Quiz q, @PathVariable("emp_id") Integer id)
	{
		quizInterface.addQuiz(q, id);
		return q;
	}
	
	@GetMapping("/getallquizs")
	public List<Quiz> allQuizs()
	{
		return quizInterface.GetAllQuizs();
		
	}
	
	@PutMapping("/modifyquiz/{comp_id}")
	public Quiz modifyquiz(@RequestBody Quiz q, @PathVariable("comp_id") Integer id)
	{
		return quizInterface.ModifyQuiz(q, id);
		
	}
	@DeleteMapping("/deletequiz/{comp_id}")
	public void modifyQuiz(@PathVariable("comp_id") Integer id)
	{
		quizInterface.deleteQuiz(id);
		log.info("delete with success");
		
	}
	@GetMapping("/assignquestiontoquiz/{idquestion}/{idquiz}")
	public void assignquestionbyid(@PathVariable("idquestion") Integer questionid,
			                             @PathVariable("idquiz") Integer quizid)
	{
		 quizInterface.assignQuestionToQuizById(questionid,quizid);
		
	}
	@GetMapping("/makequiz/{idquiz}")
	public Quiz makequiz(@PathVariable("idquiz") Integer quizid)
	{
		 return quizInterface.makeQuizaumatique(quizid);
		
	}
	@PostMapping("/result/{idemp}")
	public Result getresult(@RequestBody Quiz q,@PathVariable("idemp")Integer Id )
	{
		 return quizInterface.getResult(q,Id);
		
	}
}
