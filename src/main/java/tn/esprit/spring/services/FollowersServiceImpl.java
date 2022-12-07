package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Followers;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.FollowRepository;
@Service
public class FollowersServiceImpl implements FollowerService {
	@Autowired
	FollowRepository followersRepositoey ;
	@Autowired
	EmployeeRepository UsertRepository;

	@Autowired
	ClientRepository clientRepo;
	@Override
	public List<Followers> retrieveAllFollowerss() {
		
		return (List<Followers>) followersRepositoey.findAll() ;
	}

	@Override
	public Followers addFollowers(Followers e ,long id,long id2) {
		
		Client c= clientRepo.findById(id).orElse(null);
		
		Client c2=clientRepo.findById(id2).orElse(null);
		e.setFrom(c);
		e.setTo(c2);
		return followersRepositoey.save(e);
	}

	@Override
	public void deleteFollowers(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Followers updateFollowers(Followers c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Followers retrieveFollowers(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
