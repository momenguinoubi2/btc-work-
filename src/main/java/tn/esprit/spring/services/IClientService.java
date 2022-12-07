package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Client;


public interface IClientService {

	List<Client> retrieveAllClients();
	
	List<Client> retrieveClientByName(String name);

	Client addClient(Client c);

	void deleteClient(Long id);

	Client updateClient(long id,Client c);

	Client retrieveClient(Long id);
	
	void forgetPassword(String email);

	Client updateProfession(long id, Client c);
	Client updateDomain(long id, Client c);

}
