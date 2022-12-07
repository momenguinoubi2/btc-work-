package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.BadWords;

public interface BadWordsService {
	List<BadWords> retrieveAllBadWords();

	void addBadWords(BadWords e,int idpost);

	void deleteBadWords(int id);

	void updateBadWords(BadWords e,int id);
	public void addBadWords1(BadWords e);
	public List retrieveCooment(int id) ;
}
