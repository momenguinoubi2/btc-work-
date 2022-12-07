package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Role;

public interface IRoleService {
	List<Role> retrieveAllRoles();

	Role addRole(Role c);

	void deleteRole(int id);

	Role updateRole(Role c);

	Role retrieveRole(int id);
}
