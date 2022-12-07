package tn.esprit.spring.controller;

import java.util.LinkedHashSet;
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
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.services.IComplainService;

@RestController
@Api(tags = "Gestion des complains")
@RequestMapping("/complains")
@Slf4j
public class ComplainContoller {
	
	@Autowired
    IComplainService complainService;
	
	@PostMapping("/addcomplain/{emp_id}")
	public Complain addComplain(@RequestBody Complain c, @PathVariable("emp_id") Integer id)
	{
		complainService.addComplain(c,id);
		return c;
	}
	
	@GetMapping("/getallcomplains")
	public List<Complain> allComplains()
	{
		return complainService.getAllComplains();
		
	}
	
	@GetMapping("/Employeebysentiment")
	public LinkedHashSet<Employee> getemployeesbysentiment()
	{
		return complainService.listemployeebyorderbysentiment();
		
	}
	
	@PutMapping("/modifycomplain/{comp_id}")
	public Complain modifycomplian(@RequestBody Complain c, @PathVariable("comp_id") Integer id)
	{
		return complainService.editComplain(c,id);
		
	}
	@DeleteMapping("/deletecomplains/{comp_id}")
	public void modifycomplian(@PathVariable("comp_id") Integer id)
	{
		complainService.deleteComplain(id);
		log.info("delete with success");
		
	}
	
	

}
