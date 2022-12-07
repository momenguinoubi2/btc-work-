package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.services.IEmployeeService;

@RestController
@Api(tags = "Gestion des Employees")
@RequestMapping("/employee")
public class EmployeeRestController {
	@Autowired
	IEmployeeService employeeService;
	// http://localhost:8089/SpringMVC/employee/retrieve-all-employees
		@ApiOperation(value = "Récupérer la liste des employees")
		@GetMapping("/retrieve-all-employees")
		public List<Employee> getEmployees() {
			List<Employee> listEmployees = employeeService.retrieveAllEmployees();
			return listEmployees;
		}

		// http://localhost:8088/SpringMVC/employee/retrieve-employee/1
		@ApiOperation(value = "Récupérer un employee par Id")
		@GetMapping("/retrieve-employee/{employee-id}")
//		@ApiResponses(value = {
//				@ApiResponse(code = 200, message = "Success|OK"),
//				@ApiResponse(code = 401, message = "Not Authorized!"),
//				@ApiResponse(code = 403, message = "Forbidden!"),
//				@ApiResponse(code = 404, message = "Not Found!") })
		public Employee retrieveEmployee(@PathVariable("employee-id") int employeeId) {
			return employeeService.retrieveEmployee(employeeId);
		}

		@PostMapping("/add-employee/{identreprise}")
		public Employee addClient(@RequestBody Employee e,@PathVariable("identreprise") int identreprise) {
			return employeeService.addEmployee(e,identreprise);
		}

		// http://localhost:8088/SpringMVC/employee/remove-employee/1
		@DeleteMapping("/remove-employee/{employee-id}")
		public void removeEmployee(@PathVariable("employee-id") int employeeId) {
			employeeService.deleteEmployee(employeeId);
		}

		// http://localhost:8088/SpringMVC/employee/modify-employee
		@PutMapping("/modify-employee/{identreprise}")
		public Employee modifyEmployee(@RequestBody Employee e,@PathVariable("identreprise") int identreprise) {
			return employeeService.updateEmployee(e, identreprise);
		}
}
