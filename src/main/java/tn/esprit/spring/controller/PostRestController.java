package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.services.LikeService;
import tn.esprit.spring.services.PostsService;

@RestController
@Api(tags = "Gestion des post")
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	PostsService postxervice;
	@Autowired
	LikeService likesservice;
	@CrossOrigin(origins = "*")
	@GetMapping("/retrive-posts")
	public List<Post> getPosts() {
		List<Post> listPost = postxervice.retrieveAllPosts();
		return listPost;
	}
	@CrossOrigin(origins = "*")
	@DeleteMapping("/remove-post/{post-id}")
	public void removeEmployee(@PathVariable("post-id") int employeeId) {
		postxervice.deletePosts(employeeId);
	}
	@CrossOrigin(origins = "*")
		@PostMapping("/add-post/{User_id}")
		public void AddPost(@RequestBody Post p,@PathVariable("User_id") long userid)
		{
			postxervice.addPosts(p,userid);
		}
		@PutMapping("/modify-post/{post-Id}/{user_id}")
		public void modifyEmployee(@RequestBody Post e,@PathVariable("post-Id") int postId,@PathVariable("user_id") int userid) {
			postxervice.updatePosts(e,postId,userid);
		}
		
		@GetMapping("/retrive-employe-with-most-posts")
		public Employee getEmployeWithMostPosts() {
			Employee emp = postxervice.retrieveBestPosts();
			return emp;
			
		}
		@CrossOrigin(origins = "*")
		@GetMapping("/retrive-Posts-with-most-likes")
		public Post getMostPosts() {
			Post post =postxervice.retrieveBestPosts1() ;
			return post;
		
	}
		@PostMapping("/send")
		public void get() {
			postxervice.sendMAil();
			
			}
}
	
	


