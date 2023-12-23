package com.mg.tutor.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("tutor")
@AllArgsConstructor
public class TutorController {

	@Autowired
	private TutorService tutorService;
	
	@GetMapping
	public Flux<Tutor> getAll(){
		System.out.println("All Tutor info");
		return tutorService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Tutor> getById(@PathVariable final int id){
		System.out.println("Get one Tutor by id");
		return tutorService.getById(id);
	}
	
	@PutMapping("{id}")
	public Mono<Tutor> updateById(@PathVariable("id") final int id, @RequestBody final Tutor tutor){
		System.out.println("Update a Tutor info");
		return tutorService.update(id, tutor);
	}
	
	@PostMapping
	public Mono<Tutor> save(@RequestBody Tutor tutor){
		System.out.println("Added a Tutor");
		return tutorService.save(tutor);
	}
	
	@DeleteMapping("{id}")
	public Mono<Tutor> delete(@PathVariable("id") final int id){
		System.out.println("A Tutor deleted");
		return tutorService.delete(id);
	}
}
