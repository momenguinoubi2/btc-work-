package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.utils.MailConfig;
import tn.esprit.spring.utils.MyConstants;

@Service
public class InvitationService implements IInvitationService {
	
	@Autowired
	InvitationRepository invitationRepo;
	
	//@Autowired
    //public JavaMailSender emailSender;
	
	@Autowired
	MailConfig mailConf;
	
	//SimpleMailMessage message = new SimpleMailMessage();

	@Override
	public List<Invitation> retrieveAllInvitations() {
		// TODO Auto-generated method stub
		
		
		
		List<Invitation> listInv= invitationRepo.findAll();
		return listInv;
	}
	
	@Override
	public List<Invitation> retrieveUnacceptedInvitation() {
		// TODO Auto-generated method stub
		
		
		
		List<Invitation> listInv= invitationRepo.InvitationStatus("false");
		return listInv;
	}

	@Override
	public void addInvitation(List<Invitation> c) {
		Date date = new Date();
		String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		
		
		        
		// TODO Auto-generated method stub
		
		for (Invitation inv : c) {
			
			
			
			
			//message.setTo(MyConstants.FRIEND_EMAIL);
	       // message.setSubject("Test Simple Email");
	        //message.setText("Hello, Im testing Simple Email");

	        // Send Message!
	       // this.emailSender.send(message);
			inv.setDateInvitation(date);
			
			inv.setStatus("false");
			
			invitationRepo.save(inv);
			mailConf.send(inv.getEmail(), inv.getEmail(), "<h4>to accept the invitation click on this link:</h4>"+"http://localhost:4200/invitation/"+inv.getIdInvitation()+"   <img src='https://media.discordapp.net/attachments/776190120927821846/950523223693484032/1646486323389.jpeg'>");
		}
		
	}
	
	@Override
	public void sendInacceptedInvitation(List<Invitation> c) {
		
for (Invitation inv : c) {
			
			
			mailConf.send(inv.getEmail(), inv.getEmail(), "to accept the invitation click on this link: http://localhost:4200/invitation/"+inv.getIdInvitation());
		}
		
	}
	
	@Override
	public void sendInvitation(int id) {
		
		Invitation inv = invitationRepo.findById(id).orElse(null);
					
					
					mailConf.send(inv.getEmail(), inv.getEmail(), "to accept the invitation click on this link: http://localhost:4200/invitation/"+inv.getIdInvitation());
				
				
			}
	

	@Override
	public void deleteInvitation(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Invitation updateInvitation(Invitation c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invitation retrieveInvitation(int id) {
		// TODO Auto-generated method stub
		
		Invitation inv = invitationRepo.findById(id).orElse(null);
		inv.setStatus("true");
		invitationRepo.save(inv);
		return inv;
	}

}
