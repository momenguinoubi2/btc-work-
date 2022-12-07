package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Integer> {
//	@Query("SELECT e FROM Employee e JOIN Complains c ON e.idEmployee=c.employee.idEmployee ORDER BY c.SentimentType")
//	List<Employee> EmployeeBySentiment();
	
	@Query(value="SELECT MAX(nbre_projet) FROM `employee`",nativeQuery = true)
	// le nbre de projet le plus grand 
		int getMaxProject();
	@Query(value="SELECT * FROM `employee` e where e.nbre_projet=:nbreProjet ",nativeQuery = true)
	//employee qui a le nbre de projet le plus grand 
		List<Employee> getEmployeeMaxProject(@Param("nbreProjet") int nbreProjet);
	@Query(value="SELECT id_employee FROM employee e INNER JOIN project p WHERE p.specialite=e.specialite AND p.id_project=:idProject",nativeQuery = true)
	//employee qui a le nbre de projet le plus grand 
		List<Integer> getEmployeeProjectSpecialite(@Param("idProject") int idProject);
	

			@Query(value="SELECT Max(employees_id_employee) FROM `task` t where t.etat_remise='inTime'AND t.projects_id_project=:idProject",nativeQuery = true)
	//employee qui a le nbre de projet le plus grand 
		int employeeTaskInTime(@Param("idProject") int idProject);

			@Query(value="SELECT Min(employees_id_employee) FROM `task` t where t.etat_remise='inTime'AND t.projects_id_project=:idProject",nativeQuery = true)
	//employee qui a le nbre de projet le plus grand 
		int employeeTaskInTimeMin(@Param("idProject") int idProject);


}
