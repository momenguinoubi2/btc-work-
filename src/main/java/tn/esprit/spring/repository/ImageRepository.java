package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Image;



public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);
}
