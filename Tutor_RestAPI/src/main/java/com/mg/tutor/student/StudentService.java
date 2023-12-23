package com.mg.tutor.student;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Flux<Student> getAll(){
		return studentRepository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Student> getById(final int id){
		return studentRepository.findById(id);
	}
	
	public Mono<Student> update(final int id, Student student){
		return studentRepository.save(student);
	}
	
	public Mono<Student> save(Student student){
		return studentRepository.save(student);
	}
	
	public Mono<Student> delete(final int id){
		final Mono<Student> dbStudent = getById(id);
		if(Objects.isNull(dbStudent)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(studentToBeDeleted ->
			studentRepository.delete(studentToBeDeleted).then(Mono.just(studentToBeDeleted)));
	}
}
