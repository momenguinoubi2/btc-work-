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
import tn.esprit.spring.entities.Reaction;
import tn.esprit.spring.services.ReactionService;

@RestController
@Api(tags = "Gestion des reactions")
@RequestMapping("/reaction")
public class ReactionRestController {
	@Autowired
	ReactionService reactionService;
	@ApiOperation(value = "Récupérer la liste des reactions")
	@GetMapping("/retrieve-all-reactions")
	public List<Reaction> getReactions() {
		List<Reaction> listReactions = reactionService.retrieveAllReactions();
		return listReactions;
	}

	// http://localhost:8088/SpringMVC/evenement/retrieve-evenement/1
	@ApiOperation(value = "Récupérer une reaction par Id")
	@GetMapping("/retrieve-reaction/{reaction-id}")
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"),
//			@ApiResponse(code = 403, message = "Forbidden!"),
//			@ApiResponse(code = 404, message = "Not Found!") })
	public Reaction retrieveReaction(@PathVariable("Reaction-id") int reactionId) {
		return reactionService.retrieveReaction(reactionId);
	}

	@PostMapping("/add-reaction")
	public Reaction addReaction(@RequestBody Reaction r) {
		return reactionService.addReaction(r);
	}
	// http://localhost:8088/SpringMVC/evenement/remove-evenement/1
			@DeleteMapping("/remove-reaction/{reaction-id}")
			public void removeReaction(@PathVariable("reaction-id") int reactionId) {
				reactionService.deleteReaction(reactionId);
			}

			// http://localhost:8088/SpringMVC/evenement/modify-evenement
			@PutMapping("/modify-reaction")
			public Reaction modifyReaction(@RequestBody Reaction r) {
				return reactionService.updateReaction(r);
			}
			@GetMapping("/getMoyenneReaction/{EvenementId}")
			public Float getMoyenne(@PathVariable("EvenementId") int evenementId ){
				return reactionService.getMoyenneReaction(evenementId);
			}
			@GetMapping("/retrive-best-event")
			public Evenement getBestEvent() {
				Evenement evenement =reactionService.getPostsWithMostLikes() ;
				return evenement;
			
		}

}
