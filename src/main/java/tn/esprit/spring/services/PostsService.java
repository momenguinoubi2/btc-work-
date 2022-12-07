package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Post;

public interface PostsService {
	List<Post> retrieveAllPosts();

	void addPosts(Post e,long idUser);

	void deletePosts(int id);

	Post updatePosts(Post e,int id,int id2);

	Post retrievePosts(int id);
	Employee retrieveBestPosts();
	public Post retrieveBestPosts1();
	public void  sendMAil();

}
