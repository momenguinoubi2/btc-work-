package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idClient;
	private String nom;
	private String prenom;
	private java.util.Date dateNaissance;
	private String email;
	private String password;
	private String domain;
	@Enumerated(EnumType.STRING)
	private Profession profession;
	//@Enumerated(EnumType.STRING)
	//private Domain domain;
	private String role;
    private boolean enabled;
    @ManyToOne
    private Role rolee;
    private int numBan;
	private int occ;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<likes> likes;
	
	
	private String imageName;

	
	private String type;
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="employees")
	private Set<Comment> comments;*/
	 
}
