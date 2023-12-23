package com.mg.tutor.appointment;

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
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public Flux<Appointment> getAll(){
		return appointmentRepository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Appointment> getById(final int id){
		return appointmentRepository.findById(id);
	}
	
	public Mono<Appointment> update(final int id, Appointment appointment){
		return appointmentRepository.save(appointment);
	}
	
	public Mono<Appointment> save(Appointment appointment){
		return appointmentRepository.save(appointment);
	}
	
	public Mono<Appointment> delete(final int id){
		final Mono<Appointment> dbAppointment = getById(id);
		if(Objects.isNull(dbAppointment)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(appointmentToBeDeleted ->
			appointmentRepository.delete(appointmentToBeDeleted).then(Mono.just(appointmentToBeDeleted)));
	}
}
