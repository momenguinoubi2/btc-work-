package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Entreprise   implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntreprise;
	
	 private String nomEntreprise, CodeTva, email,password;
	 private int numTel;

	@Enumerated(EnumType.STRING)
	private Domain domain;
	
	 @ToString.Exclude
		@JsonIgnore

		@ManyToMany(cascade = CascadeType.PERSIST)
		private Set<Project> projects;
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprises")
		private Set<Quiz> quizs;
	 @ToString.Exclude
		@JsonIgnore

		@ManyToOne
		private Voyage voyages;
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprises")
		private Set<Employee> employees;
	@ToString.Exclude
	@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprises")
		private Set<ParticipationEvenement> participationEvenements;
	 
	 
}
