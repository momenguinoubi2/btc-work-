package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.IRoleRepository;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	IRoleRepository roleRepo;

	@Override
	public List<Role> retrieveAllRoles() {
		// TODO Auto-generated method stub
		List<Role> listClient= roleRepo.findAll();
		return listClient;
	}

	@Override
	public Role addRole(Role c) {
		// TODO Auto-generated method stub
		roleRepo.save(c);
		return c;
	}

	@Override
	public void deleteRole(int id) {
		// TODO Auto-generated method stub
		roleRepo.deleteById(id);
		
	}

	@Override
	public Role updateRole(Role c) {
		// TODO Auto-generated method stub
		roleRepo.save(c);
		return c;
	}

	@Override
	public Role retrieveRole(int id) {
		// TODO Auto-generated method stub
		Role c = roleRepo.getById(id);
		return c;
	}
	
	

}
