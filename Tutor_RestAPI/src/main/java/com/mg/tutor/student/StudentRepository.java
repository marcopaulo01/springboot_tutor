package com.mg.tutor.student;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentRepository extends ReactiveMongoRepository<Student,Integer>{

}
