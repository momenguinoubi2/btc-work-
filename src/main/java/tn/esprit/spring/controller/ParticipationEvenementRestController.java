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
import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.services.EvenementService;
import tn.esprit.spring.services.IEmployeeService;

@RestController
@Api(tags = "Gestion des participation evenements")
@RequestMapping("/participationevenement")
public class ParticipationEvenementRestController {
	@Autowired
	EvenementService participationevenementService;
	@Autowired
	IEmployeeService employeeService;
	// http://localhost:8088/SpringMVC/evenement/retrieve-all-evenement
		@ApiOperation(value = "Récupérer la liste des evenements")
		@GetMapping("/retrieve-all-evenements")
		public List<Evenement> getEvenements() {
			List<Evenement> listEvenements = participationevenementService.retrieveAllEvenements();
			return listEvenements;
		}

		// http://localhost:8088/SpringMVC/evenement/retrieve-evenement/1
		@ApiOperation(value = "Récupérer un evenement par Id")
		@GetMapping("/retrieve-evenement/{evenement-id}")
//		@ApiResponses(value = {
//				@ApiResponse(code = 200, message = "Success|OK"),
//				@ApiResponse(code = 401, message = "Not Authorized!"),
//				@ApiResponse(code = 403, message = "Forbidden!"),
//				@ApiResponse(code = 404, message = "Not Found!") })
		public Evenement retrieveEvenement(@PathVariable("evenement-id") int evenementId) {
			return participationevenementService.retrieveEvenement(evenementId);
		}

		@PostMapping("/add-evenement")
		public Evenement addEvenement(@RequestBody Evenement e) {
			return participationevenementService.addEvenement(e);
		}

		// http://localhost:8088/SpringMVC/evenement/remove-evenement/1
		@DeleteMapping("/remove-evenement/{evenement-id}")
		public void removeEvenement(@PathVariable("evenement-id") int evenementId) {
			participationevenementService.deleteEvenement(evenementId);
		}

		// http://localhost:8088/SpringMVC/evenement/modify-evenement
		@PutMapping("/modify-evenement")
		public Evenement modifyEvenement(@RequestBody Evenement e) {
			return participationevenementService.updateEvenement(e);
		}

}
