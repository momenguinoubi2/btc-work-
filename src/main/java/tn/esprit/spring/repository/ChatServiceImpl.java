package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Employee;
import tn.esprit.spring.entities.Group;

public class ChatServiceImpl implements ChatService{
	private static int idSession = 1;
	@Autowired
	ChatRepository chatRepo;
	@Autowired
	EmployeeRepository userRepo;
	@Autowired
	GroupRepository groupRepo;

	@Override
	public Chat SendMessage(Chat chatMessage, Long idGroup) {
		Group group=groupRepo.findById(idGroup).get();
		chatMessage.setChatGroup(group);
		Employee emp = userRepo.findById(idSession).get();
		chatMessage.setMessageUser(emp);
		return chatRepo.save(chatMessage);
		
	}

	@Override
	public List<Chat> getMessageByGroup(Long idGroup) {
		Group group = groupRepo.findById(idGroup).orElse(null);
		
		return chatRepo.getChatByGroup(group);
	}

	@Override
	public List<Chat> getAllMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteMessage(Long idMessage) {
		// TODO Auto-generated method stub
		
	}

}
