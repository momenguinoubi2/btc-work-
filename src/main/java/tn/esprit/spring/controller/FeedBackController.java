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
import tn.esprit.spring.entities.Complain;
import tn.esprit.spring.entities.FeedBack;
import tn.esprit.spring.services.IFeedBackService;

@RestController
@Api(tags = "Gestion des FeedBack")
@RequestMapping("/FeedBack")
@Slf4j
public class FeedBackController {

	@Autowired
	IFeedBackService backService;
	
	@PostMapping("/addfeedback/{emp_id}")
	public FeedBack addFeedBack(@RequestBody FeedBack c, @PathVariable("emp_id") Integer id)
	{
		backService.addFeedback(c,id);
		return c;
	}
	
	@GetMapping("/getallfeedbacks")
	public List<FeedBack> allFeedBacks()
	{
		return backService.ListFeedbacks() ;
		
	}
	
	@PutMapping("/modifyfeedback/{comp_id}")
	public FeedBack modifyFeedBack(@RequestBody FeedBack c, @PathVariable("comp_id") Integer id)
	{
		return backService.modifyFeedback(c);
		
	}
	@DeleteMapping("/deletefeedback/{comp_id}")
	public void deleteFeedBack(@PathVariable("comp_id") Integer id)
	{
		backService.deleteFeedback(id);
		log.info("delete with success");
		
	}
	
	
}
