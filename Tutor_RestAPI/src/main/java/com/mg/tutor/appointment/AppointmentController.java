package com.mg.tutor.appointment;

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
@RequestMapping("appointment")
@AllArgsConstructor
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public Flux<Appointment> getAll(){
		System.out.println("All Appointment info");
		return appointmentService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Appointment> getById(@PathVariable final int id){
		System.out.println("Get one Appointment by id");
		return appointmentService.getById(id);
	}
	
	@PutMapping("{id}")
	public Mono<Appointment> updateById(@PathVariable("id") final int id, @RequestBody final Appointment appointment){
		System.out.println("Update a Appointment info");
		return appointmentService.update(id, appointment);
	}
	
	@PostMapping
	public Mono<Appointment> save(@RequestBody Appointment appointment){
		System.out.println("Added a Appointment");
		return appointmentService.save(appointment);
	}
	
	@DeleteMapping("{id}")
	public Mono<Appointment> delete(@PathVariable("id") final int id){
		System.out.println("A Appointment deleted");
		return appointmentService.delete(id);
	}
}
