package tn.esprit.spring.controller;

import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Image;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.ImageRepository;
import tn.esprit.spring.utils.ImageUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin(origins = "*") // open for all ports
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired
    ClientRepository clientRepo;

    @CrossOrigin(origins = "*")
    @PostMapping("/upload/image/{client-id}")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file,@PathVariable("client-id") Long clientId)
            throws IOException {
    	
    	Client c= clientRepo.findById(clientId).get();

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        
        
        
        c.setImageName(file.getOriginalFilename());
        clientRepo.save(c);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}