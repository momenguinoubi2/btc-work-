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
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.services.IResponseService;

@RestController
@Api(tags = "management of  Responses")
@RequestMapping("/Response")
@Slf4j
public class ResponseController {
     
	@Autowired
	IResponseService responseService;
	@PostMapping("/addResponse/{emp_id}")
	public Response addResponse(@RequestBody Response q, @PathVariable("emp_id") Integer id)
	{
		responseService.addResponse(q, id);
		return q;
	}
	
	@GetMapping("/getallResponses")
	public List<Response> allResponses()
	{
		return responseService.ListResponses();
		
	}
	
	@PutMapping("/modifyResponse/{comp_id}")
	public Response modifyResponse(@RequestBody Response q, @PathVariable("comp_id") Integer id)
	{
		return responseService.modifyResponse(q);
		
	}
	@DeleteMapping("/deleteResponse/{comp_id}")
	public void modifyResponse(@PathVariable("comp_id") Integer id)
	{
		responseService.deleteResponse(id);
		log.info("delete with success");
		
	}
}
