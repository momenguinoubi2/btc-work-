package tn.esprit.spring.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
public class Followers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFollow;
	
	//private String FollowerName;
	//private String ClientName;
	//private int ClientId;
	@NonNull private java.util.Date dateFollow;
	@ToString.Exclude
	@ManyToOne
    @JoinColumn(name="from_user_fk")
    private Client from;

    @ManyToOne
    @JoinColumn(name="to_user_fk")
    private Client to;
}