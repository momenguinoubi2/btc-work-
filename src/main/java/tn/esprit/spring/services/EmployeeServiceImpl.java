package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
@Autowired
EmployeeRepository employeeRepository;
@Autowired
EntrepriseRepository entrepriseRepository;
	@Override
	public List<Employee> retrieveAllEmployees() {
		// TODO Auto-generated method stub
		return  (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee addEmployee(Employee e,int idEntreprise) {
		// TODO Auto-generated method stub
	Entreprise entreprise= entrepriseRepository.findById(idEntreprise).orElse(null);
	e.setEntreprises(entreprise);
		return employeeRepository.save(e);
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee e,int idEntreprise) {
		// TODO Auto-generated method stub
		Entreprise entreprise= entrepriseRepository.findById(idEntreprise).orElse(null);
		e.setEntreprises(entreprise);
		return employeeRepository.save(e);
	}

	@Override
	public Employee retrieveEmployee(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

}
