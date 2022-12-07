package tn.esprit.spring.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Group {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idGroup")
	
	private Long idGroup;
	private String groupeName;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateGroupe;
	
	private String imageGroup;
	
	@ManyToMany
	@JsonIgnore
	private Set<Employee> GroupUser;

}