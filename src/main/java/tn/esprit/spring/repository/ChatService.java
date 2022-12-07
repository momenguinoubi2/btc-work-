package tn.esprit.spring.repository;

import java.util.List;

import tn.esprit.spring.entities.Chat;

public interface ChatService {
	public Chat SendMessage(Chat chatMessage ,Long to);
	public List<Chat> getMessageByGroup(Long idGroup);
	public List<Chat> getAllMessage();
	public void DeleteMessage(Long idMessage);
}
