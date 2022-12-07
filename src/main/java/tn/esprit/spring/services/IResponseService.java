package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Response;

public interface IResponseService {
	Response addResponse(Response back,  Integer  emp_id);
	Response modifyResponse(Response back);
	Response findResponse(Integer back);
	List<Response> ListResponses();
	void deleteResponse(Integer id);
}
