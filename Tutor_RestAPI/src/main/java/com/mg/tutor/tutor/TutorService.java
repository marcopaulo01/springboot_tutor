package com.mg.tutor.tutor;

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
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;
	
	public Flux<Tutor> getAll(){
		return tutorRepository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Tutor> getById(final int id){
		return tutorRepository.findById(id);
	}
	
	public Mono<Tutor> update(final int id, Tutor tutor){
		return tutorRepository.save(tutor);
	}
	
	public Mono<Tutor> save(Tutor tutor){
		return tutorRepository.save(tutor);
	}
	
	public Mono<Tutor> delete(final int id){
		final Mono<Tutor> dbTutor = getById(id);
		if(Objects.isNull(dbTutor)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(tutorToBeDeleted ->
			tutorRepository.delete(tutorToBeDeleted).then(Mono.just(tutorToBeDeleted)));
	}
}
