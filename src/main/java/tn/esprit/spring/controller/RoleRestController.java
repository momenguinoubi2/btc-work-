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
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IRoleService;

@RestController
@Api(tags = "Gestion des Roles")
@RequestMapping("/Roles")
public class RoleRestController {
	
	@Autowired
	IRoleService roleService;

	// http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@ApiOperation(value = "Récupérer la liste des roles")
	@GetMapping("/retrieve-all-Roles")
	public List<Role> getRoles() {
		List<Role> listRoles = roleService.retrieveAllRoles();
		return listRoles;
	}

	// http://localhost:8089/SpringMVC/client/retrieve-client/8
	@ApiOperation(value = "Récupérer un Role par Id")
	@GetMapping("/retrieve-role/{role-id}")
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Success|OK"),
//			@ApiResponse(code = 401, message = "Not Authorized!"),
//			@ApiResponse(code = 403, message = "Forbidden!"),
//			@ApiResponse(code = 404, message = "Not Found!") })
	public Role retrieveRole(@PathVariable("role-id") int roleId) {
		return roleService.retrieveRole(roleId);
	}

	@PostMapping("/add-role")
	public Role addRole(@RequestBody Role c) {
		return roleService.addRole(c);
	}

	// http://localhost:8089/SpringMVC/client/remove-client/{client-id}
	@DeleteMapping("/remove-role/{role-id}")
	public void removeRole(@PathVariable("role-id") int roleId) {
		roleService.deleteRole(roleId);
	}

	// http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-role")
	public Role modifyRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}

}
