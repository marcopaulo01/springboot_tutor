package com.mg.tutor.subject;

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
@RequestMapping("subject")
@AllArgsConstructor
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@GetMapping
	public Flux<Subject> getAll(){
		System.out.println("All Subjects info");
		return subjectService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Subject> getById(@PathVariable final int id){
		System.out.println("Get one Subject by id");
		return subjectService.getById(id);
	}
	
	@PutMapping("{id}")
	public Mono<Subject> updateById(@PathVariable("id") final int id, @RequestBody final Subject subject){
		System.out.println("Update a Subject info");
		return subjectService.update(id, subject);
	}
	
	@PostMapping
	public Mono<Subject> save(@RequestBody Subject subject){
		System.out.println("Added a Subject");
		return subjectService.save(subject);
	}
	
	@DeleteMapping("{id}")
	public Mono<Subject> delete(@PathVariable("id") final int id){
		System.out.println("A Subject deleted");
		return subjectService.delete(id);
	}
}
