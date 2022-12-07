package tn.esprit.spring.entities;



import java.util.Date;

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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Result {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idResult;
	@Enumerated(EnumType.STRING)
	private Badge badge;
	private Integer Score;
	
	@Temporal(TemporalType.DATE)
	private Date creationdate;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
	
	@ManyToOne
	@JsonIgnore
	private Quiz quiz;
	
	
	
	
}