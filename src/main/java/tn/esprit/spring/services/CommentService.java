package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Post;

public interface CommentService {
	List<Comment> retrieveAllComment();

	void addComment(Comment e,int idpost ,long iduser);
	void deletePosts(int id);

	void updateComment(Comment e,int id);
	public void addComment1(Comment e);
	public List retrieveCooment(int id) ;

	
}
