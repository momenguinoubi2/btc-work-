package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Dislike;

public interface DislikeService {
	List<Dislike> retrieveAllRates();

	Dislike addRate(Dislike e);

	void deletePosts(int id);

	Dislike updateComment(Dislike e);

	Post retrievePostWithMostDisDislike();
	List<Dislike> retrieveListPostWithMostDisDislike();
}
