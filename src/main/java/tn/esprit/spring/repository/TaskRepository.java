package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository <Task,Integer>{
	@Query(value="select  COUNT(*) FROM task where projects_id_project=:idProject  ",nativeQuery = true)
	//nbre des task d un projet
		int nbrTaskByProject(@Param("idProject") int idProject);
	@Query(value="select  COUNT(*) FROM task where projects_id_project=:idProject AND etat='done'  ",nativeQuery = true)
	//nbre des task done par projet
		int nbrTaskDoneByProject(@Param("idProject") int idProject);
	
	@Query(value="select  COUNT(*) FROM task where projects_id_project=:idProject AND etat_remise='inTime'  ",nativeQuery = true)
	//nbre des task intime par projet
		int nbrTaskInTimeByProject(@Param("idProject") int idProject);
	
}
