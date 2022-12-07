package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Invitation;
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
	
	@Query("Select i FROM Invitation i where i.status = :status")
	List<Invitation> InvitationStatus(@Param("status") String status);

}
