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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.places.placesRentals.documents.Place;
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
}
