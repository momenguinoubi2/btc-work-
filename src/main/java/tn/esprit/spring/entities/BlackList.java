package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@RequiredArgsConstructor
public class BlackList implements Serializable {
			private static final long serialVersionUID = 1L;

			@Id
		
			private int idEmployeeBlackList;
			
			@NonNull private String  email,password;
			@NonNull private int numTel;

			@NonNull private String FirstName, LastName, formation;
			@NonNull private float salaire,prime=0;
			@NonNull private int nbreProjet=0;
			@Temporal (TemporalType.DATE)
			@NonNull private java.util.Date dateNaissance;

			@Enumerated(EnumType.STRING)
			private Profession profession;
			@Enumerated(EnumType.STRING)
			private Specialite specialite;
}
