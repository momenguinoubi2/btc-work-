package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Reaction implements Serializable{
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idFeedback;
		private int note;
		@NonNull private String commentaire;
		@Temporal (TemporalType.DATE)
		@NonNull private java.util.Date dateFeedback;
		
		@ToString.Exclude
		@ManyToOne
		private Evenement evenements;

	

}
