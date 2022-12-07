package tn.esprit.spring.services;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Participation;
import tn.esprit.spring.entities.Vote;
import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.repository.VoteRepository;
import tn.esprit.spring.repository.VoyageRepository;
@EnableScheduling
@Service
@Slf4j
public class VoyageServiceImpl implements IVoyageService{
	
	@Autowired
	VoyageRepository voyageRepository;
	@Autowired
	ParticipationRepository participationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public List<Voyage> RetrieveAllVoyages() {
	   List<Voyage> listVoyage= voyageRepository.findAll();
	   for(Voyage v:listVoyage)
	  {
		log.info("Voyage:" + v.getDepVille()+ v.getDesVille());
	  }
	  return listVoyage;
	}

	@Override
	public Voyage addVoyage(Voyage v) {
		voyageRepository.save(v);
		return v;
	}

	@Override
	public void deleteVoyage(int id) {
		voyageRepository.deleteById(id);
		
	}

	@Override
	public Voyage updateVoyage(Voyage v) {
		voyageRepository.save(v);
		return v;
	}

	@Override
	public Voyage retrieveVoyage(int id) {
		Voyage v= voyageRepository.findById(id).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+id));
		return v;
	}

	@Override
	public Participation addParticipation(int voyageId, int employeeId) throws Exception {
		Voyage v= voyageRepository.findById(voyageId).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+voyageId));
		Employee e= employeeRepository.findById(employeeId).orElseThrow(()->new IllegalArgumentException("no employee with id="+employeeId));
		List<Participation> listOfParticipation=e.getParticipation().stream().filter(p->{
			if(p.getVoyages().getDateRetour().before(v.getDateDep()) ||  p.getVoyages().getDateDep().after(v.getDateRetour())){
				return false;
			}
			return true;
		}).collect(Collectors.toList());
		if(listOfParticipation.size()>0){
			throw new Exception("Employe posséde un voyage déja");
		}
		Participation participation = new Participation();
		participation.setEmployees(e);
		participation.setVoyages(v);
		sendEmail(e);
		return participationRepository.save(participation);
		
	}

	@Override
	public List<Employee> findEmployeeAffectedToVoyageByEntrepriseId(int entrepriseId) {
		Entreprise entreprise=entrepriseRepository.findById(entrepriseId).orElseThrow(()->new IllegalArgumentException("No entreprise found with id = "+entrepriseId));
		return entreprise.getEmployees().stream().filter(e->
			 e.getParticipation().size()>0
		).collect(Collectors.toList());
	}
	//@Scheduled(cron = "*/5 * * * * *" )
		@Override
		public void updateNbreIntervenant() {
			// TODO Auto-generated method stub
			List<Participation> participations=   RetrieveAllParticipationss();
			for (Participation pp :participations)
			{
				int idv= pp.getVoyages().getIdVoyage();
				Voyage v = voyageRepository.findById(idv).orElse(null);
				v.setNbIntervenant(voyageRepository.getNbreIntervenant(idv));
				voyageRepository.save(v);
			}
			
		}

		@Override
		public List<Participation> RetrieveAllParticipationss() {
			// TODO Auto-generated method stub
			return (List<Participation>) participationRepository.findAll();
		}

		@Override
		public void voterVoyage(int voageId, int employeId,float note) {
			Voyage v= voyageRepository.findById(voageId).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+voageId));
			Employee e= employeeRepository.findById(employeId).orElseThrow(()->new IllegalArgumentException("no employee with id ="+employeId));
			Vote vote=new Vote();
			vote.setNote(note);
			vote.setEmployee(e);
			vote.setVoyage(v);
			voteRepository.save(vote);
			
		}

		@Override
		public Float getMoyenneVote(int voyageId) {
			Voyage v= voyageRepository.findById(voyageId).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+voyageId));
			if(v.getVotes().size()==0){
				return (float) 0;
			}
			float res=0;
			
				for (Vote vote : v.getVotes()) {
					res+=vote.getNote();
				}
			res=res/v.getVotes().size();
			
			return res;
			

		}
		@Override
	    public BufferedImage generateQRCode(String urlText) throws Exception {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode("fronturl/voyage/"+urlText, BarcodeFormat.QR_CODE, 200, 200);

	        return MatrixToImageWriter.toBufferedImage(bitMatrix);
	    }
		public void sendEmail (Employee emp) throws UnsupportedEncodingException, MessagingException{
			String subject = "Participation";
			String senderName = "promoesprit@gmail.com";
			String mailContent = "<p>Cher " + emp.getFirstName() + emp.getLastName() + ",</p>";
			mailContent += "<p> Vous etes l'un des participants du voyage </p>";
			
			
			mailContent += "<p> Merci<br> promoesprit@gmail.com</p>";
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom("promoesprit@gmail.com", senderName);
			helper.setTo(emp.getEmail());
			helper.setSubject(subject);
			helper.setText(mailContent, true);
			
			mailSender.send(message);
			System.out.println("email sent");
			
		}
	
	
}
