package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;
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

@RequiredArgsConstructor
public class Employee  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmployee;
	
	 private String  email,password;
	 private int numTel;

	 private String FirstName, LastName;
	 private float salaire,prime=0;
	 private int nbreProjet=0;
	@Temporal (TemporalType.DATE)
	 private java.util.Date dateNaissance;
	 private int numBan;
	 private int occ;
	@Enumerated(EnumType.STRING)
	private Profession profession;
	@Enumerated(EnumType.STRING)
	private Specialite specialite;
	// @ToString.Exclude
		//@JsonIgnore

		/*@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<Comment> comments;*/
	
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<FeedBack> feedBacks;

	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<Participation> participation;
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<Complain> complains;
	 @ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
		private Set<Result> results ;
	
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<ParticipationProject> participationProjects;
	 @ToString.Exclude
		@JsonIgnore

		@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
		private Set<Task> tasks;
	@ToString.Exclude
		@JsonIgnore

		@ManyToOne
		private Entreprise entreprises;
	/* @OneToMany(cascade = CascadeType.REMOVE, mappedBy="employees")
		private Set<likes> likes;
		@OneToMany(mappedBy="to")
	    private List<Followers> followers;

	    @OneToMany(mappedBy="from")
	    private List<Followers> following;*/
	    
		 @JsonIgnore
		 @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
			private Set<Vote> votes;
}
