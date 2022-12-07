package tn.esprit.spring.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.Parser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.services.IInvitationService;
import tn.esprit.spring.utils.MyConstants;
import tn.esprit.spring.utils.xlsParser;

@RestController
@Api(tags = "Gestion des invitation")
@RequestMapping("/Invitation")
public class InvitationRestController {
	
	List<Invitation> parser = new  xlsParser().parser();
	
	
	@Autowired
	IInvitationService invitationService;
	
	@Autowired
    public JavaMailSender emailSender;
	
	@PostMapping("/add-Invitations")
	public void ajouterListeInvitations(@RequestBody List<Invitation> inv){
		invitationService.addInvitation(inv);
	}
	
	@ResponseBody
    @RequestMapping("/sendHtmlEmail")
    public String sendHtmlEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;
        
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        
        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
        
        message.setContent(htmlMsg, "text/html");
        
        helper.setTo(MyConstants.FRIEND_EMAIL);
        
        helper.setSubject("Test send HTML email");
        
    
        this.emailSender.send(message);

        return "Email Sent!";
    }
	@CrossOrigin(origins = "*")
	@GetMapping("/add-InvitationsExcel")
	public void ajouterListeInvitationsFromExcel(){
		invitationService.addInvitation(parser);
	}
	
	@PostMapping("/sendUnacceptedInvitations")
	//@Scheduled(cron = "*/30 * * * * *" )
	public void sendUnacceptedInvitations(){
		invitationService.sendInacceptedInvitation(invitationService.retrieveUnacceptedInvitation());
	}
	@CrossOrigin(origins = "*")
	@ApiOperation(value = "Récupérer invitation")
	@GetMapping("/retrieve-invitation/{inv-id}")
	public Invitation retrieveInvitation(@PathVariable("inv-id") int invId) {
		//xlsParser xls = new xlsParser();
		//xls.parser();
		//System.out.println("xlssss test test"+ xlsParser.nbrows);
		return invitationService.retrieveInvitation(invId);
	}
	
	@ApiOperation(value = "Récupérer invitation")
	@CrossOrigin(origins = "*")
	@GetMapping("/send-invitation/{inv-id}")
	public void sendInvitation(@PathVariable("inv-id") int invId) {
		//xlsParser xls = new xlsParser();
		//xls.parser();
		//System.out.println("xlssss test test"+ xlsParser.nbrows);
		 invitationService.sendInvitation(invId);
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/retrieveUnacceptedInvitations")
	@ResponseBody
	public List<Invitation> getUnacceptedInvitations() {

		List<Invitation> listInvitations = invitationService.retrieveUnacceptedInvitation();
		return listInvitations;
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/retrieveInvitations")
	@ResponseBody
	public List<Invitation> getInvitations() {

		List<Invitation> listInvitations = invitationService.retrieveAllInvitations();
		return listInvitations;
	}

}
