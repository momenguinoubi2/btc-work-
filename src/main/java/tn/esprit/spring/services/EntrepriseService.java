package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
@Service
public class EntrepriseService implements IEntrepriseService {
	
	@Autowired
	EntrepriseRepository entrepriseRepo;

	@Override
	public List<Entreprise> retrieveAllEntreprises() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise addEntreprise(Entreprise e) {
		// TODO Auto-generated method stub
		entrepriseRepo.save(e);
		return null;
	}

	@Override
	public void deleteEntreprise(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entreprise updateEntreprise(Entreprise e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise retrieveEntreprise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
