package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ParticipationProject;

@Repository
public interface ParticipationProjectRepository extends CrudRepository< ParticipationProject, Integer> {
	@Query(value="select  COUNT(*)  from participation_project p where p.projects_id_project=:idProject AND p.employees_id_employee=:idEmployee ",nativeQuery = true)
	//verification si la participation existe ou non
		int nbrParticip(@Param("idProject") int idProject, @Param("idEmployee") int idEmployee);
	
	//nbre de participation de chaque emloyee dans les projets
	@Query(value="select  COUNT(*)  from participation_project p where  p.employees_id_employee=:idEmployee ",nativeQuery = true)
	
		int nbreParticipationOfEmployee( @Param("idEmployee") int idEmployee);
	
	
}
