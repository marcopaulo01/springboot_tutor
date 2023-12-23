package com.mg.tutor.subject;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SubjectRepository extends ReactiveMongoRepository<Subject,Integer>{

}
