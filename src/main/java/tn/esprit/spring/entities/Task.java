package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Task  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTask;
	@Temporal (TemporalType.DATE)
	@NonNull private java.util.Date dateTask= new Date(System.currentTimeMillis());
	@Temporal (TemporalType.DATE)

	@NonNull private java.util.Date dateFinTask;
	@Temporal (TemporalType.DATE)

	@NonNull private java.util.Date dateRemise=null;
	@NonNull private String nom, description;
	@Enumerated(EnumType.STRING)
	private Etat etat=Etat.todo;
	@Enumerated(EnumType.STRING)
	private EtatRemise etatRemise;
	@ToString.Exclude
	@JsonIgnore

	@ManyToOne
	private Project projects;
	@ToString.Exclude
	@JsonIgnore

	@ManyToOne
	private Employee employees;
	
}
