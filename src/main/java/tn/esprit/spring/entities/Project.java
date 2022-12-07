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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@RequiredArgsConstructor
public class Project  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProject;
	@NonNull private String description, libelle, nom, documents;
	@Temporal (TemporalType.DATE)

	@NonNull private java.util.Date dateDebut, dateFin;
	@NonNull private float budget;
	@NonNull private int intervenant=0;
	@NonNull private int nbreTaskInTime=0, nbreTaskLate=0,nbreTask=0;
	@Enumerated(EnumType.STRING)
	
	private Etat etat= Etat.pending;
@Enumerated(EnumType.STRING)
	
	private Specialite specialite;
	
	 @ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy="projects")
		private Set<ParticipationProject> participationProjects;

	//@ToString.Exclude
	//@JsonIgnore

	//@ManyToOne
	//private Entreprise entreprises;
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="projects")
		private Set<Task> tasks;
	
	
}
