package com.mg.tutor.student;

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
@RequestMapping("student")
@AllArgsConstructor
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public Flux<Student> getAll(){
		System.out.println("All Student info");
		return studentService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Student> getById(@PathVariable final int id){
		System.out.println("Get one Student by id");
		return studentService.getById(id);
	}
	
	@PutMapping("{id}")
	public Mono<Student> updateById(@PathVariable("id") final int id, @RequestBody final Student student){
		System.out.println("Update a Student info");
		return studentService.update(id, student);
	}
	
	@PostMapping
	public Mono<Student> save(@RequestBody Student student){
		System.out.println("Added a Student");
		return studentService.save(student);
	}
	
	@DeleteMapping("{id}")
	public Mono<Student> delete(@PathVariable("id") final int id){
		System.out.println("A Student deleted");
		return studentService.delete(id);
	}
}
