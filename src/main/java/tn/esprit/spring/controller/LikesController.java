package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.likes;
import tn.esprit.spring.services.LikeService;
import tn.esprit.spring.services.PostsService;

@RestController
@Api(tags = "Gestion des like")
@RequestMapping("/like")
public class LikesController {
	@Autowired
	PostsService postxervice;
	@Autowired
	LikeService likesservice;
	@GetMapping("/retrive-Posts-with-most-likes")
	public Post getMostPosts() {
		Post post =likesservice.retrievePostWithMostlikess() ;
		return post;
	
}
	@GetMapping("/retrive-list-Posts-with-most-likes")
	public List<likes> getMostLikesPosts() {
		List<likes> post =likesservice.retrieveListPostWithMostlikess() ;
		return post;
	
}
	@CrossOrigin(origins = "*")
	@PostMapping("/add-Comment/{Post-id}/{user-id}")
	public void addComment(@RequestBody likes e,@PathVariable("Post-id") int postIdi,@PathVariable("user-id") int postIdu) {
		//System.out.print(e.toString());
		likesservice.addRate(e,postIdi,postIdu);
		
	}
	

}