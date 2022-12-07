package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Badge;
import tn.esprit.spring.entities.Difficulty;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Result;
import tn.esprit.spring.entities.Type;

public interface IResultService {
	List<Result> GetAllResults();
	Integer resultbytype(Integer emi,Type type,Badge badge);
	Employee employeeofthemonth();

}
