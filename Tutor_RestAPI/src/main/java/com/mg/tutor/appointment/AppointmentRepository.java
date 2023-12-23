package com.mg.tutor.appointment;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AppointmentRepository extends ReactiveMongoRepository<Appointment,Integer>{

}
