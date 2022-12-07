package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.entities.ParticipationEvenement;

public interface EvenementService {
	List<Evenement> retrieveAllEvenements();

	Evenement addEvenement(Evenement e);

	void deleteEvenement(int id);

	Evenement updateEvenement(Evenement e);

	Evenement retrieveEvenement(int id);
	void AssignEntrepriseToEvenement(int ide, int idevenement);
	void updateNbreIntervenantEvenement();
	List<ParticipationEvenement> retrieveAllParticipationEvenements();
	List<Evenement> getEvenementForEntreprise(int ide);
	//void sendEmail();

}
