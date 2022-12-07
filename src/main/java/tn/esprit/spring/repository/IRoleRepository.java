package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Role;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
