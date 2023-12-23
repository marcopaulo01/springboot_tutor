package com.mg.tutor.tutor;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TutorRepository extends ReactiveMongoRepository<Tutor,Integer>{

}
