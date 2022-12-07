package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.BadWords;

@Repository
public interface BadWordsRepo extends CrudRepository<BadWords ,Integer > {

}
