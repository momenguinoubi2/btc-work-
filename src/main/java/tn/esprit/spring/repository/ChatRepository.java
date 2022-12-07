package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Group;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	@Query("SELECT c from Chat c where c.ChatGroup=:group")
	List<Chat> getChatByGroup(@Param("group") Group group);
}
