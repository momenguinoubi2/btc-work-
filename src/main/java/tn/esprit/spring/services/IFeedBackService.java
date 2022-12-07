package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.FeedBack;

public interface IFeedBackService {
          
	
	FeedBack addFeedback(FeedBack back,  Integer  emp_id);
	FeedBack modifyFeedback(FeedBack back);
	FeedBack findFeedback(FeedBack back);
	List<FeedBack> ListFeedbacks();
	void deleteFeedback(Integer id);
	
}
