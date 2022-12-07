package tn.esprit.spring.services;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Participation;
import tn.esprit.spring.entities.Voyage;

public interface IVoyageService {
	
	List<Voyage> RetrieveAllVoyages();
	List<Participation> RetrieveAllParticipationss();
	Voyage addVoyage(Voyage v);
	
	void deleteVoyage(int id);
	
	Voyage updateVoyage(Voyage v);
	
	Voyage retrieveVoyage(int id);
	
	Participation addParticipation(int voyageId, int employeeId) throws Exception;
	
	List<Employee> findEmployeeAffectedToVoyageByEntrepriseId(int entrepriseId);
	void updateNbreIntervenant();
	void voterVoyage(int voageId,int employeId,float note);
	Float getMoyenneVote(int voyageId);
	public  BufferedImage generateQRCode(String urlText) throws Exception;
	public void sendEmail (Employee emp) throws UnsupportedEncodingException, MessagingException;
}
