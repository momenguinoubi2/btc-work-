package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Complain;
import tn.esprit.spring.entities.FeedBack;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.FeedBackRepository;

@Service
@Slf4j
public class FeedBackServiceImpl implements IFeedBackService {
	
	@Autowired
	FeedBackRepository feedBackRepository;
	@Autowired
	EmployeeRepository emRepository;
	@Override
	public FeedBack addFeedback(FeedBack back , Integer  emp_id) {
		Date now = new Date();
		back.setDateFeedback(now);
	    back.setEmployees(emRepository.findById(emp_id).get());
	    feedBackRepository.save(back);
		return back;
	}

	@Override
	public FeedBack modifyFeedback(FeedBack back) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeedBack findFeedback(FeedBack back) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FeedBack> ListFeedbacks() {
		List<FeedBack> lco = (List<FeedBack>) feedBackRepository.findAll();
		 for (FeedBack FeedBack : lco) {
			log.info("this is the body: "+FeedBack.getBody());
		}
		 return lco;
	}

	@Override
	public void deleteFeedback(Integer back) {
		feedBackRepository.deleteById(back);
		
	}

}
