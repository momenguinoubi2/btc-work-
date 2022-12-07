package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Complain;
@Repository
public interface ComplainRepository extends CrudRepository<Complain, Integer> {

	
}
