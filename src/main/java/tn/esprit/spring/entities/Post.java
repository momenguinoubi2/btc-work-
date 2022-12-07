package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
//@RequiredArgsConstructor
@ToString
public class Post  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPost;
	 private String titre, body;
	
	 private java.util.Date datePost;
	@ToString.Exclude
	@JsonIgnore
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy="posts")
		private Set<Comment> comments;
	// @ToString.Exclude
//@OneToMany(cascade = CascadeType.REMOVE,mappedBy="posts")
//private Set<Like> likes;
		 @ToString.Exclude
         @JsonIgnore
		@OneToMany(cascade = CascadeType.REMOVE, mappedBy="posts")
		private Set<likes> likes;
		 @ToString.Exclude

		@OneToMany(cascade = CascadeType.REMOVE, mappedBy="posts")
		 @JsonIgnore
		private Set<Dislike> Dislike;
@ManyToOne
private Client c;

}