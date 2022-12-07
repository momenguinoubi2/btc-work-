package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.FeedBack;

@Repository
public interface FeedBackRepository extends CrudRepository<FeedBack, Integer> {

}
