package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.BadWords;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repository.BadWordsRepo;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.EmployeeRepository;
import tn.esprit.spring.repository.LikeRepository;
import tn.esprit.spring.repository.PostRepository;
@Service
public class PostsServiceImpl implements PostsService {
	@Autowired
	PostRepository postRepository;
	@Autowired
	EmployeeRepository emRepository;
	
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	BadWordsRepo badwords;
	@Autowired
	LikeRepository LikeRepository;
	@Autowired

	private JavaMailSender javaMailSender;
	@Autowired
	private JavaMailSender javamil;
	@Autowired
	mailservice Ms;
	//@Override

	@Override
	public List<Post> retrieveAllPosts() {
		 List<Post>listpost= (List<Post>) postRepository.findAll();
		 return listpost; 
		
	}

	@Override
	public void addPosts(Post e,long idUser) {
		
		//e.setDatePost(now);
		Client client=clientRepo.findById(idUser).get();
//Employee employee=emRepository.findById(idUser).get();
	String Body = e.getBody();
	String titre=e.getTitre();
	List<BadWords> BadWordss = (List<BadWords>) badwords.findAll();

	boolean checkBody = checkBadWords(BadWordss,Body);
	boolean checkTitle = checkBadWords(BadWordss,titre);
	if(client.getNumBan()==0&&client.getOcc()<5)
	{
	if(checkBody == true || checkTitle == true)
	{
		System.out.print("Offensive language detected, publication failed.");
		int occ=client.getOcc()+1;
		client.setOcc(occ);
		clientRepo.save(client);
	}else if (checkBody == false || checkTitle== false ) 
	{
	
	e.setC(client);
		 postRepository.save(e);}
	}}

	@Override
	public void deletePosts(int id) {
		// TODO Auto-generated method stub
		postRepository.deleteById(id);
	}

	@Override
	public Post updatePosts(Post e,int idpost,int idEmp) {
		Employee client=emRepository.findById(idEmp).get();
		String Body = e.getBody();
		String titre=e.getTitre();
		List<BadWords> BadWordss = (List<BadWords>) badwords.findAll();

		boolean checkBody = checkBadWords(BadWordss,Body);
		boolean checkTitle = checkBadWords(BadWordss,titre);
		if(checkBody == true || checkTitle == true)
		{
			System.out.print("Offensive language detected, publication failed.");
		}else if (checkBody == false && checkTitle== false ) 
		e.setIdPost(idpost);
		return postRepository.save(e);
	}

	@Override
	public Post retrievePosts(int id) {
		// TODO Auto-generated method stub
		return postRepository.findById(id).get();
	}
	public boolean checkBadWords(List<BadWords> words, String message) 
	{
		
		int iterator=0;
		for(int i=0 ; i<words.size(); i++)
		{
			if (message.contains(words.get(i).getNom()))
			{
				iterator++; 
			}
		}
		if (iterator>0)
		{
			
			System.out.print("Your review contains offensive language, "+iterator+" inappropriate words found.");
			return true;
		}
		System.out.print("Your review is clean, valid and ready for publication.");
			return false;	
	}

	@Override
	public Employee retrieveBestPosts() {
		 int idEmploye = postRepository.getEmployeWithMostPosts();
		return emRepository.findById(idEmploye).get();
		
	}
	@Override
	public Post retrieveBestPosts1() {
		 int idpost = LikeRepository.getPostsWithMostLikes();
		return postRepository.findById(idpost).get();
		
	}
	@Override
	public void  sendMAil() {
		Date date = new Date();
		String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    SimpleMailMessage message= new SimpleMailMessage();
		List<Employee> listEmployee=(List<Employee>) emRepository.findAll();
		List<Employee>k= listEmployee.stream().filter(e->e.getOcc()>5).collect(Collectors.toList());
		for(Employee in: k)
		{
			try {
				Ms.sendBanEmail(in);
				
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
		}
		
		
		
	
}}
