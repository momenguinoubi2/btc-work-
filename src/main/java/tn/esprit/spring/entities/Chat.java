package tn.esprit.spring.entities;

import java.util.Date;
import java.util.Set;

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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class Chat {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Chat;
	private Long idMessage ;
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMsg;
	
	@ManyToOne
	//@ToString.Exclude
	@JsonIgnore
	Group ChatGroup;
	
	@ManyToOne
	Employee MessageUser; 

}