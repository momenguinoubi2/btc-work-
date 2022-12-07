package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Followers;
@Repository

public interface FollowRepository extends CrudRepository <Followers, Integer> {

}
