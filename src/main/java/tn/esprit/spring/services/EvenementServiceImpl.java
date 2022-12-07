package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.entities.ParticipationEvenement;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.EvenementRepository;
import tn.esprit.spring.repository.ParticipationEvenementRepository;

@EnableScheduling
@Service
public class EvenementServiceImpl implements EvenementService {
	@Autowired
	EvenementRepository evenementRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	ParticipationEvenementRepository participationEvenementRepository;

	/*
	 * @Autowired MailService mailService;
	 */
	@Override
	public List<Evenement> retrieveAllEvenements() {
		// TODO Auto-generated method stub
		return (List<Evenement>) evenementRepository.findAll();
	}

	@Override
	public List<ParticipationEvenement> retrieveAllParticipationEvenements() {
		// TODO Auto-generated method stub
		return (List<ParticipationEvenement>) participationEvenementRepository.findAll();
	}

	@Override
	public Evenement addEvenement(Evenement e) {
		// TODO Auto-generated method stub
		return evenementRepository.save(e);
	}

	@Override
	public void deleteEvenement(int id) {
		// TODO Auto-generated method stub
		evenementRepository.deleteById(id);
	}

	@Override
	public Evenement updateEvenement(Evenement e) {
		// TODO Auto-generated method stub
		return evenementRepository.save(e);
	}

	@Override
	public Evenement retrieveEvenement(int id) {
		// TODO Auto-generated method stub
		return evenementRepository.findById(id).get();
	}

	@Override
	public void AssignEntrepriseToEvenement(int ide, int idevenement) {
		// TODO Auto-generated method stub
		Entreprise e = entrepriseRepository.findById(ide).orElse(null);
		Evenement evenement = evenementRepository.findById(idevenement).orElse(null);
		ParticipationEvenement pe = new ParticipationEvenement();
		pe.setEvenements(evenement);
		pe.setEntreprises(e);

		participationEvenementRepository.save(pe);
		updateNbreIntervenantEvenement();
	}

	//@Scheduled(cron = "*/5 * * * * *")
	@Override
	public void updateNbreIntervenantEvenement() {
		// TODO Auto-generated method stub
		List<ParticipationEvenement> participationEvenement = retrieveAllParticipationEvenements();
		for (ParticipationEvenement pp : participationEvenement) {
			int idp = pp.getEvenements().getIdEvenement();
			Evenement e = evenementRepository.findById(idp).orElse(null);
			e.setNbreIntervenant(participationEvenementRepository.getNbreIntervenant(idp));
			evenementRepository.save(e);
		}

	}

	@Override
	public List<Evenement> getEvenementForEntreprise(int ide) {
		// TODO Auto-generated method stub
		List<Evenement> evenementFilter = new ArrayList<Evenement>();
		List<Integer> Idevenement = evenementRepository.getEvenementEntrepriseDomain(ide);
		for (int idevenement : Idevenement) {
			Evenement e = evenementRepository.findById(idevenement).orElse(null);

			evenementFilter.add(e);
		}
		return evenementFilter;

	}
	/*
	 * @Override public void sendEmail() { List<Entreprise>
	 * listEmployee=(List<Entreprise>) entrepriseRepository.findAll();
	 * for(Entreprise in: listEmployee) { try { mailService.sendWarningEmail(in);;
	 * 
	 * } catch (MailException mailException) { System.out.println(mailException); }
	 * 
	 * } }
	 */
}
