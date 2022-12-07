package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import tn.esprit.spring.services.BlackListService;

@RestController
@Api(tags = "Gestion des blackLists")
@RequestMapping("/blackList")
public class BlackListRestController {
	@Autowired
	BlackListService blackListService;
	//http://localhost:8089/SpringMVC/blackList/update-employee/1
	
	@PostMapping("/update-employee/{idEmployee}")
	public void updateEmployee(  @PathVariable("idEmployee") int idEmployee) {
		blackListService.setFormation(idEmployee);
	}
}
