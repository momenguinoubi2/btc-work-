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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@RequiredArgsConstructor
public class Evenement implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvenement;
	@NonNull private String nomEvenement, libelle,description,localisation;
	private int nbreIntervenant=0;
	@Enumerated(EnumType.STRING)
	private Domain domain;
	//@Temporal (TemporalType.DATE)
	@NonNull private java.util.Date datedebEvenement;
	//@Temporal (TemporalType.DATE)
	@NonNull private java.util.Date datefinEvenement;
	 @ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy="evenements")
		private Set<ParticipationEvenement> participationEvenements;
	 @ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy="evenements")
		private Set<Reaction> Reactions;

}
