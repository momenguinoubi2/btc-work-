package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class ParticipationProject implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idParticipProject;
	@Temporal (TemporalType.DATE)
	@NonNull private java.util.Date dateParticipation=new Date(System.currentTimeMillis());
	@ToString.Exclude
	@JsonIgnore

	@ManyToOne
	private Project projects;
	 @ToString.Exclude
		@JsonIgnore

		@ManyToOne
		private Employee employees;
}
