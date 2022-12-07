package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Employee;
@Repository
public interface CommentRepository extends CrudRepository <Comment, Integer> {

}
