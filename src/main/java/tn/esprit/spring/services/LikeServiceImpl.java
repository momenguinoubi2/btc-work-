package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.likes;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.LikeRepository;
//import tn.esprit.spring.repository.likesRepository;
//import tn.esprit.spring.repository.likesRepository;
import tn.esprit.spring.repository.PostRepository;
@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	PostRepository postRepository;
	@Autowired
	LikeRepository likesRepository;
	@Autowired
	EmployeeRepository emRepository;

	@Autowired
	ClientRepository clientRepo;
	@Override
	public List<likes> retrieveAllRates() {
		// TODO Auto-generated method stub
		return  (List<likes>) likesRepository.findAll();
	}

	@Override
	public likes addRate(likes e,int id,long iduser) {
		Date date = new Date();
		String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		Post p = postRepository.findById(id).orElse(null);
		Client c=clientRepo.findById(iduser).orElse(null);
		//p.getComments().add(e);
		e.setPosts(p);
		e.setClient(c);
		e.setDateDislike(date);
		likesRepository.save(e);
		return null;
	}

	@Override
	public void deletePosts(int id) {
		// TODO Auto-generated method stub
		likesRepository.deleteById(id);
		
	}

	@Override
	public likes updateComment(likes e) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Post retrievePostWithMostlikess() {
		 int idpost= likesRepository.getPostsWithMostLikes();
		return postRepository.findById(idpost).get();
		
	}

	@Override
	public List<likes> retrieveListPostWithMostlikess() {
		
		return (List<likes>) likesRepository.getListPostsWithMostLikes();	
			
	}
		}

