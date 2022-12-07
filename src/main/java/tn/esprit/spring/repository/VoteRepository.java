package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


}
