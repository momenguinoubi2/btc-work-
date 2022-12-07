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
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseService;
import tn.esprit.spring.services.IEmployeeService;

@RestController
@Api(tags = "Gestion des entreprises")
@RequestMapping("/entreprise")
public class EntrepriseRestController {
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	IEmployeeService employeeService;
	// http://localhost:8088/SpringMVC/entreprise/retrieve-all-entreprise
		@ApiOperation(value = "Récupérer la liste des entreprises")
		@GetMapping("/retrieve-all-entreprise")
		public List<Entreprise> getEntreprises() {
			List<Entreprise> listEntreprises = entrepriseService.retrieveAllEntreprises();
			return listEntreprises;
		}

		// http://localhost:8088/SpringMVC/entreprise/retrieve-entreprise/1
		@ApiOperation(value = "Récupérer un entreprise par Id")
		@GetMapping("/retrieve-entreprise/{entreprise-id}")
//		@ApiResponses(value = {
//				@ApiResponse(code = 200, message = "Success|OK"),
//				@ApiResponse(code = 401, message = "Not Authorized!"),
//				@ApiResponse(code = 403, message = "Forbidden!"),
//				@ApiResponse(code = 404, message = "Not Found!") })
		public Entreprise retrieveEntreprise(@PathVariable("entreprise-id") int entrepriseId) {
			return entrepriseService.retrieveEntreprise(entrepriseId);
		}

		@PostMapping("/add-entreprise")
		public Entreprise addEntreprise(@RequestBody Entreprise e) {
			return entrepriseService.addEntreprise(e);
		}

		// http://localhost:8088/SpringMVC/entreprise/remove-entreprise/1
		@DeleteMapping("/remove-entreprise/{entreprise-id}")
		public void removeEntreprise(@PathVariable("entreprise-id") int entrepriseId) {
			entrepriseService.deleteEntreprise(entrepriseId);
		}

		// http://localhost:8088/SpringMVC/entreprise/modify-entreprise
		@PutMapping("/modify-entreprise")
		public Entreprise modifyEntreprise(@RequestBody Entreprise e) {
			return entrepriseService.updateEntreprise(e);
		}
}
