package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Invitation;


public interface IInvitationService {
	
	List<Invitation> retrieveAllInvitations();
	
	List<Invitation> retrieveUnacceptedInvitation();
	
	void sendInacceptedInvitation(List<Invitation> c);

	void addInvitation(List<Invitation> c);

	void deleteInvitation(int id);

	Invitation updateInvitation(Invitation c);

	Invitation retrieveInvitation(int id);
	
	public void sendInvitation(int id);

}
