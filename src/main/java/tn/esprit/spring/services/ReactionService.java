package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Evenement;
import tn.esprit.spring.entities.Reaction;

public interface ReactionService {
	List<Reaction> retrieveAllReactions();

	Reaction addReaction(Reaction r);

	void deleteReaction(int id);

	Reaction updateReaction(Reaction r);

	Reaction retrieveReaction(int id);
	void AssignReactionToEvenement(int idr, int idevenement);
	//void noterEvenement(int evenementId,int reactionId,float note);
	Float getMoyenneReaction(int evenementId);
	public Evenement getPostsWithMostLikes();

}
