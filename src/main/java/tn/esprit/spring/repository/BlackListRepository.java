package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.BlackList;
@Repository
public interface BlackListRepository extends CrudRepository<BlackList, Integer> {
	

			@Query(value="SELECT COUNT(*) FROM `task` WHERE  etat_remise='delayed' AND employees_id_employee=:idEmployee  AND projects_id_project=:idProject",nativeQuery = true)
	//nbre de task late dans un projet specifique par emeployee 
		int nbreTaskLate(@Param("idEmployee") int idEmployee,@Param("idProject")int idProject);
			@Query(value="SELECT COUNT(*) FROM `task` WHERE employees_id_employee=:idEmployee  AND projects_id_project=:idProject",nativeQuery = true)
			//nbre de task late dans un projet specifique par emeployee 
				int nbreTask(@Param("idEmployee") int idEmployee,@Param("idProject")int idProject);
			
			@Query(value="SELECT projects_id_project FROM `task` GROUP BY projects_id_project",nativeQuery = true)
			//nbre de task late dans un projet specifique par emeployee 
				List<Integer> retrieveAllProjectsHasTasks();
			@Query(value="SELECT employees_id_employee FROM `task` GROUP BY employees_id_employee",nativeQuery = true)
			//nbre de task late dans un projet specifique par emeployee 
				List<Integer> retrieveAllEmployeesHasTasks();

}
