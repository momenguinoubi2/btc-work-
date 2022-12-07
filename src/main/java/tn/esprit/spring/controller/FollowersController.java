package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entities.Followers;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.services.FollowerService;

@RestController
@Api(tags = "Gestion des Followers")
@RequestMapping("/Follow")
public class FollowersController {
	@Autowired
	FollowerService fl;
	@PostMapping("/ajoutFollow/{idFollowfrom}/{idFollowto}")
	public Followers addFollowers(@RequestBody Followers e,@PathVariable("idFollowfrom") int id1,@PathVariable("idFollowto") int id2) {
	return fl.addFollowers(e, id1, id2);
	}
	
	

}
