package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Domaines;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.DomainRepository;
import tn.esprit.spring.utils.MailConfig;
@Service
@Slf4j
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	DomainRepository domainRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	MailConfig mailConf;

	@Override
	public List<Client> retrieveAllClients() {
		List<Client> listClient= clientRepository.findAll();
		for(Client c:listClient)
		{
			log.info("Client:" + c.getNom()+ c.getPrenom());
		}
		return listClient;
	}

	@Override
	public Client addClient(Client c) {
		clientRepository.save(c);
		return c;
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);

	}
	
	@Override
	public Client updateProfession(long id,Client c) {
		
		Client cl= clientRepository.findById(id).get();
		c.setIdClient(id);
		c.setDateNaissance(cl.getDateNaissance());
		c.setEmail(cl.getEmail());
		c.setEnabled(cl.isEnabled());
		c.setNom(cl.getNom());
		c.setPrenom(cl.getPrenom());
		c.setProfession(cl.getProfession());
		c.setRole(cl.getRole());
		c.setPassword(cl.getPassword());
		clientRepository.save(c);
		return c;
	}
	
	@Override
	public Client updateDomain(long id,Client c) {
		
		List<Domaines> listDomains = domainRepo.findAll();
		Domaines domaines=new Domaines();
		
		Client cl= clientRepository.findById(id).get();
		c.setIdClient(id);
		c.setDateNaissance(cl.getDateNaissance());
		c.setEmail(cl.getEmail());
		c.setEnabled(cl.isEnabled());
		c.setNom(cl.getNom());
		c.setPrenom(cl.getPrenom());
		c.setProfession(cl.getProfession());
		c.setRole(cl.getRole());
		c.setPassword(cl.getPassword());
		for(Domaines d:listDomains)
		{
			if(d.getNom().equalsIgnoreCase(c.getDomain())) {
				c.setDomain(c.getDomain());
				System.out.println("existe");
				break;
			}
			else {
				domaines.setNom(c.getDomain());
				domainRepo.save(domaines);
				c.setDomain(c.getDomain());
				System.out.println("n existe pas");
			}
		}
		
		
		clientRepository.save(c);
		return c;
	}

	@Override
	public Client updateClient(long id,Client c) {
		
		Client cl= clientRepository.findById(id).get();
		c.setIdClient(id);
		c.setDateNaissance(cl.getDateNaissance());
		c.setEmail(cl.getEmail());
		c.setEnabled(cl.isEnabled());
		c.setNom(cl.getNom());
		c.setPrenom(cl.getPrenom());
		c.setProfession(cl.getProfession());
		c.setRole(cl.getRole());
		c.setPassword(passwordEncoder.encode(c.getPassword()));
		System.out.println("password test "+c.getPassword());
		clientRepository.save(c);
		Boolean isMatch = passwordEncoder.matches("Admin123.",c.getPassword());
		System.out.println("ismatch"+isMatch);
		return c;
	}

	@Override
	public Client retrieveClient(Long id) {
		Client c= clientRepository.findById(id).get();
		return c;
	}
	@Override
	public List<Client> retrieveClientByName(String name) {
		List<Client> c= clientRepository.findByNom(name);
		return c;
	}

	@Override
	public void forgetPassword(String email) {
		// TODO Auto-generated method stub
		Client c = clientRepository.findByEmailAddress(email);
		mailConf.send(c.getEmail(), c.getEmail(), "Click on the link to reset your password http://localhost:4200/changePassword/"+c.getIdClient());
		
	}

}
