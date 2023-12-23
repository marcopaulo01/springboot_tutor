package com.mg.tutor.subject;

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
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public Flux<Subject> getAll(){
		return subjectRepository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Subject> getById(final int id){
		return subjectRepository.findById(id);
	}
	
	public Mono<Subject> update(final int id, Subject subject){
		return subjectRepository.save(subject);
	}
	
	public Mono<Subject> save(Subject subject){
		return subjectRepository.save(subject);
	}
	
	public Mono<Subject> delete(final int id){
		final Mono<Subject> dbSubject = getById(id);
		if(Objects.isNull(dbSubject)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(subjectToBeDeleted ->
				subjectRepository.delete(subjectToBeDeleted).then(Mono.just(subjectToBeDeleted)));
	}
}
