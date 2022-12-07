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
public class Voyage  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVoyage;
	@NonNull private String depVille, desVille;
private int nbIntervenant;
	@NonNull 
	@Temporal(TemporalType.DATE)
	private java.util.Date dateDep, dateDest,dateRetour;
	

	@Enumerated(EnumType.STRING)
	private Object object;
	 @ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy="voyages")
		private Set<Participation> participation;
	 @JsonIgnore
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="voyage")
		private Set<Vote> votes;
}