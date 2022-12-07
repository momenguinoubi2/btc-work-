package tn.esprit.spring.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Badge;
import tn.esprit.spring.entities.Difficulty;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.entities.Result;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.repository.ResultRepository;

@Service
@Slf4j
public class ResultServiceImpl implements IResultService {
	
	
	@Autowired
	ResultRepository resultRepository;
	@Override
	public List<Result> GetAllResults() {
	List<Result> lco = (List<Result>) resultRepository.findAll();
	 for (Result result : lco) {
		log.info("this is the Result ");
	}
	 return lco;
	}
	@Override
	public Integer resultbytype(Integer emi, Type type, Badge badge) {
		// TODO Auto-generated method stub
		return resultRepository.Listresultwithtype(emi,type, badge);
	}
	@Override
	public Employee employeeofthemonth() {
		// TODO Auto-generated method stub
		Date now = new Date();
		now.setMonth(Calendar.MONTH-1);
		return resultRepository.employeofthmonth(now,Badge.GOLD);
	}
	
	

}
