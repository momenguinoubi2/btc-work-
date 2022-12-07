package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.entities.ParticipationEvenement;
import tn.esprit.spring.entities.Reaction;
import tn.esprit.spring.entities.ReactionEvenement;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.EvenementRepository;
import tn.esprit.spring.repository.ParticipationEvenementRepository;
import tn.esprit.spring.repository.ReactionEvenementRepository;
import tn.esprit.spring.repository.ReactionRepository;
@EnableScheduling
@Service
public class ReactionServiceImpl implements ReactionService{
	@Autowired
	ReactionRepository reactionRepository;
	@Autowired
	EvenementRepository evenementRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	ParticipationEvenementRepository participationEvenementRepository;
	@Autowired
	ReactionEvenementRepository reactionEvenementRepository;
	
	@Override
	public List<Reaction> retrieveAllReactions() {
		// TODO Auto-generated method stub
		return (List<Reaction>) reactionRepository.findAll() ;
	}

	@Override
	public Reaction addReaction(Reaction r) {
		// TODO Auto-generated method stub
		return reactionRepository.save(r);
	}

	@Override
	public void deleteReaction(int id) {
		// TODO Auto-generated method stub
		reactionRepository.deleteById(id);
		
	}

	@Override
	public Reaction updateReaction(Reaction r) {
		// TODO Auto-generated method stub
		return reactionRepository.save(r);
	}

	@Override
	public Reaction retrieveReaction(int id) {
		// TODO Auto-generated method stub
		return reactionRepository.findById(id).get();
	}

	@Override
	public void AssignReactionToEvenement(int idr, int idevenement) {
		Reaction r = reactionRepository.findById(idr).orElse(null);
		Evenement evenement= evenementRepository.findById(idevenement).orElse(null);
		ReactionEvenement re= new ReactionEvenement();
		re.setEvenements(evenement);
		re.setReactions(r);
		reactionEvenementRepository.save(re);
		

	}

	@Override
	public Float getMoyenneReaction(int evenementId) {
		// TODO Auto-generated method stub
		Evenement v= evenementRepository.findById(evenementId).orElseThrow(()->new IllegalArgumentException("no evenement with id ="+evenementId));
		if(v.getReactions().size()==0){
			return (float) 0;
		}
		float res=0;
		
			for (Reaction reaction : v.getReactions()) {
				res+=reaction.getNote();
			}
		res=res/v.getReactions().size();
		
		return res;
	}

	@Override
	public Evenement getPostsWithMostLikes() {
		int id_event=reactionRepository.getPostsWithMostLikes();
		return evenementRepository.findById(id_event).get();
		
	}
	
	
	
	
}
