package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Followers;

public interface FollowerService {

	List<Followers> retrieveAllFollowerss();

	Followers addFollowers(Followers c,long id,long id2);

	void deleteFollowers(Long id);

	Followers updateFollowers(Followers c);

	Followers retrieveFollowers(Long id);
}
