package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Complain;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.repository.ComplainRepository;
import tn.esprit.spring.repository.EmployeeRepository;

@Service
@Slf4j

public class ComplainServiceImpl implements IComplainService {

	@Autowired
	ComplainRepository coRepository;
	@Autowired
	EmployeeRepository emRepository;
	
	
	@Override
	public Complain addComplain(Complain c , Integer  emp_id) {
		Date now = new Date();
	    c.setDateComplain(now);
	    c.setEmployees(emRepository.findById(emp_id).get());
	    c.setSentimentType(analyse(c.getBody()));
		coRepository.save(c);
		return c;
	}

	@Override
	public Complain editComplain(Complain c, Integer id) {
		// TODO Auto-generated method stub
	    
		coRepository.save(c);
		return c;
	}

	@Override
	public void deleteComplain(Integer id) {
		coRepository.deleteById(id);
		
	}

	@Override
	public List<Complain> getAllComplains() {
		// TODO Auto-generated method stub
		 List<Complain> lco = (List<Complain>) coRepository.findAll();
		 for (Complain complain : lco) {
			log.info("this is the body: "+complain.getBody());
		}
		 return lco;
	}

	@Override
	public Complain getComplain(Integer id) {
		// TODO Auto-generated method stub
		return coRepository.findById(id).get();
	}

	@Override
	public int analyse(String Body) {
		// TODO Auto-generated method stub
		 Properties props = new Properties();
	        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	        Annotation annotation = pipeline.process(Body);
	        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
	            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	            return RNNCoreAnnotations.getPredictedClass(tree);
	        }
		return 0;
	}

	@Override
	public LinkedHashSet<Employee> listemployeebyorderbysentiment() {
		// TODO Auto-generated method stub
		 List<Complain> lco = (List<Complain>) coRepository.findAll();
		 lco.sort(Comparator.comparing(Complain::getSentimentType).reversed());
		 LinkedHashSet<Employee> Lem = new LinkedHashSet<Employee>() ;
		 for (Complain complain : lco) {
			Lem.add(complain.getEmployees());
		}
		return Lem;
	}

}
