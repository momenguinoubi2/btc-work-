package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Dislike;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.likes;
import tn.esprit.spring.repository.DislikeRepo;
import tn.esprit.spring.repository.LikeRepository;
import tn.esprit.spring.repository.PostRepository;
@Service
public class DislikeServiceIMPL implements DislikeService{
	@Autowired
	PostRepository postRepository;
	@Autowired
	DislikeRepo likesRepository;
	@Override
	public List<Dislike> retrieveAllRates() {
		return (List<Dislike>) likesRepository.findAll() ;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dislike addRate(Dislike e) {
		return likesRepository.save(e);
		
	}

	@Override
	public void deletePosts(int id) {
		// TODO Auto-generated method stub
		likesRepository.deleteById(id);;
		
	}

	@Override
	public Dislike updateComment(Dislike e) {
		return e;
		// TODO Auto-generated method stub
		
	}
	
		
	
	@Override
	public Post retrievePostWithMostDisDislike() {
		int idpost= likesRepository.getPostsWithMostLikes();
		return postRepository.findById(idpost).get();
	}

	@Override
	public List<Dislike> retrieveListPostWithMostDisDislike() {
		// TODO Auto-generated method stub
		return null;
	}

	
	}


