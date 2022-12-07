package tn.esprit.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Etat;
import tn.esprit.spring.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository <Project, Integer>{
	@Query(value="select  COUNT('employees_id_employee')  from participation_project p where p.projects_id_project=:idProject ",nativeQuery = true)
//nbre de participant dans un projet bien determine
	int getNbreIntervenant(@Param("idProject") int idProject);

	@Query(value="SELECT COUNT('projects_id_project') FROM `participation_project` p, task t WHERE p.employees_id_employee=:idEmployee ",nativeQuery = true)
	//nbre de projet dans un employee bien determine
		int getnbreProjetOfEmployee(@Param("idEmployee") int idEmployee);
	@Query(value="SELECT etat FROM `entreprise_projects` ep INNER JOIN project p WHERE  p.id_project=:idProject ",nativeQuery = true)
	//etat du projet 
		Etat getEtatOfProject(@Param("idProject") int idProject);
	@Query(value="SELECT `entreprise_id_entreprise` FROM `entreprise_projects` ep INNER JOIN project p WHERE p.id_project=:idProject ",nativeQuery = true)
	//id de l entreprise 
	List<Integer> getEntrepriseEtatProjetDone(@Param("idProject") int idProject);
	
	 @Modifying
	    @Transactional
	@Query(value="	DELETE FROM entreprise_projects WHERE projects_id_project=:idProject ",nativeQuery = true)
		void deleteProjectAssociation(@Param("idProject") int idProject);
	 
}
