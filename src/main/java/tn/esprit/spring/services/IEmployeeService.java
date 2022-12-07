package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employee;

public interface IEmployeeService {
	List<Employee> retrieveAllEmployees();

	Employee addEmployee(Employee e,int idEntreprise);

	void deleteEmployee(int id);

	Employee updateEmployee(Employee e, int idEntreprise);

	Employee retrieveEmployee(int id);
	

}
