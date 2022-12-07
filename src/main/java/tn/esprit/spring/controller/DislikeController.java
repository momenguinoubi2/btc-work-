package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entities.Dislike;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.likes;
import tn.esprit.spring.services.DislikeService;
import tn.esprit.spring.services.LikeService;
import tn.esprit.spring.services.PostsService;

@RestController
@Api(tags = "Gestion des Dislike")
@RequestMapping("/Dislike")
public class DislikeController {
	@Autowired
	PostsService postxervice;
	@Autowired
	DislikeService likesservice;
	@GetMapping("/retrive-Posts-with-most-likes")
	public List<Dislike >getMostPosts() {
		List<Dislike > post =likesservice.retrieveAllRates() ;
		return post;
	
}
	@GetMapping("/retrive-list-Posts-with-most-likes")
	public Post getMostLikesPosts() {
		Post post =likesservice.retrievePostWithMostDisDislike() ;
		return post;
	
}

}
