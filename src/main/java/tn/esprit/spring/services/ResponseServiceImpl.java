package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.ResponseRepository;

@Service
@Slf4j
public class ResponseServiceImpl implements IResponseService {

	@Autowired
	ResponseRepository responseRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Override
	public Response addResponse(Response back, Integer emp_id) {
		// TODO Auto-generated method stub
		back.setQuestions(questionRepository.findById(emp_id).get());
		responseRepository.save(back);
		return back;
	}

	@Override
	public Response modifyResponse(Response back) {
		// TODO Auto-generated method stub
		responseRepository.save(back);
		return back;
	}

	@Override
	public Response findResponse(Integer back) {
		// TODO Auto-generated method stub
		return responseRepository.findById(back).get();
	}

	@Override
	public List<Response> ListResponses() {
		List<Response> lco = (List<Response>) responseRepository.findAll();
		 for (Response response : lco) {
			log.info("this is the quiz ");
		}
		 return lco;
	}

	@Override
	public void deleteResponse(Integer id) {
		responseRepository.deleteById(id);
		
	}

}
