package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.likes;
import tn.esprit.spring.entities.Post;
//import tn.esprit.spring.entities.likess;

public interface LikeService {
	List<likes> retrieveAllRates();

	likes addRate(likes e,int id1i,long id2);

	void deletePosts(int id);

	likes updateComment(likes e);

	Post retrievePostWithMostlikess();
	List<likes> retrieveListPostWithMostlikess();


}