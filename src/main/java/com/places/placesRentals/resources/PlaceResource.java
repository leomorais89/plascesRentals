package com.places.placesRentals.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.places.placesRentals.documents.Place;
import com.places.placesRentals.dto.ImageDTO;
import com.places.placesRentals.services.PlaceService;

@RestController
@RequestMapping(value = "/places")
public class PlaceResource {

	@Autowired
	private PlaceService service;
	
	@GetMapping
	public ResponseEntity<List<Place>> findAll(){
		List<Place> places = service.findAll();
		return ResponseEntity.ok().body(places);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Place> findById(@PathVariable String id){
		Place place = service.findById(id);
		return ResponseEntity.ok().body(place);
	}
	
	@PostMapping
	public ResponseEntity<Place> insert(@RequestBody Place place){
		place = service.insert(place);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).body(place);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Place> update(@PathVariable String id, @RequestBody Place place) {
		place = service.update(id, place);
		return ResponseEntity.ok().body(place);
	}
	
	@PutMapping(value = "/{id}/alterPrice")
	public ResponseEntity<Place> alterPrice(@PathVariable String id, @RequestBody Place place){
		place = service.alterPrice(id, place);
		return ResponseEntity.ok().body(place);
	}
	
	@GetMapping(value = "/seach")
	public ResponseEntity<List<Place>> findByName(@RequestParam(value = "name", defaultValue = "") String name){
		List<Place> places = service.findByName(name);
		return ResponseEntity.ok().body(places);
	}
	
	@GetMapping(value = "/{id}/imagens")
	public ResponseEntity<List<ImageDTO>> findByPlace(@PathVariable String id){
		List<ImageDTO> imagens = service.findByPlace(id);
		return ResponseEntity.ok().body(imagens);
	}
	
	@PutMapping(value = "/{id}/addImagens")
	public ResponseEntity<List<ImageDTO>> addImagens(@PathVariable String id, @RequestBody List<ImageDTO> imagens){
		imagens = service.addImagens(id, imagens);
		return ResponseEntity.ok().body(imagens);
	}
	
	@DeleteMapping(value = "/{id}/delImagens")
	public ResponseEntity<Void> delImagens(@PathVariable String id, @RequestBody List<ImageDTO> imagens){
		service.delImagens(id, imagens);
		return ResponseEntity.noContent().build();
	}
}
