package com.mg.tutor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.mg.tutor.model.Appointment;
import com.mg.tutor.model.Student;
import com.mg.tutor.model.Subject;
import com.mg.tutor.model.Tutor;

@Controller
public class PageController {

	private final String appointmentAPI="http://localhost:8097/appointment";
	private final String studentAPI="http://localhost:8097/student";
	private final String tutorAPI="http://localhost:8097/tutor";
	private final String subjectAPI="http://localhost:8097/subject";
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/home/{id}")
	public String showHome(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		model.addAttribute("tutor",tutor);
		return "home";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
		ResponseEntity<Tutor> responseTutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class);
		
		if (responseTutor.getStatusCode().is2xxSuccessful()) {
			Tutor tutor = responseTutor.getBody();
			if( tutor.getName().equals(name)) {
				model.addAttribute("tutor",tutor);
				return "home";
			}
		}
		model.addAttribute("loginFailed", true);
        return "index";
	}
	
	@GetMapping("/register")
	public String showRegister() {
		return "add-tutor";
	}
	
	@PostMapping("/addtutor")
	public String addTutor(Tutor tutor, Model model) {
		//get highest id for tutor
		ResponseEntity<Tutor[]> response = restTemplate.getForEntity(tutorAPI, Tutor[].class);
		Tutor[] members = response.getBody();
		int maxId = 1;
		for (Tutor existingMember : members) {
			if (existingMember.getId() > maxId) {
					maxId = existingMember.getId();
			}
		}
		int newId = maxId+1;
		tutor.setId(newId);
		restTemplate.postForObject(tutorAPI, tutor, Tutor.class);
		model.addAttribute("tutor",tutor);
		return "home";
	}
	
	@GetMapping("/student/{id}")
	public String showStudent(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		model.addAttribute("tutor",tutor);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("students",students);
		return "student-list";
	}
	
	@GetMapping("/addstudent/{id}")
	public String showAddStudent(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		model.addAttribute("tutor",tutor);
		return "add-student";
	}
	
	@PostMapping("/addstudent")
	public String addStudent(@RequestParam("tutorId") int tutorId, Student student,Model model) {
		//get highest id for student
		ResponseEntity<Student[]> response = restTemplate.getForEntity(studentAPI, Student[].class);
		Student[] members = response.getBody();
	    int maxId = 1;
	    for (Student existingMember : members) {
	        if (existingMember.getId() > maxId) {
	            maxId = existingMember.getId();
	        }
	    }
	    int newId = maxId+1;
	    student.setId(newId);
	    
		//POST Student
		restTemplate.postForObject(studentAPI, student, Student.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("students",students);
		return "student-list";
	}
	
	@PostMapping("/deletestudent/{tutorId}/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId, @PathVariable("tutorId") int tutorId, Model model) {
		//DELETE Student
		restTemplate.delete(studentAPI+"/"+studentId);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("students",students);
		return "student-list";
	}
	
	@GetMapping("/editstudent/{tutorId}/{studentId}")
	public String showEditStudent(@PathVariable("studentId") int studentId, @PathVariable("tutorId") int tutorId, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		Student student = restTemplate.getForEntity(studentAPI + "/" + studentId, Student.class).getBody();
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("student",student);
		return "edit-student";
	}
	
	@PostMapping("/updatestudent")
	public String updateStudent(@RequestParam("tutorId") int tutorId, Student student,Model model) {
		restTemplate.postForObject(studentAPI, student, Student.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("students",students);
		return "student-list";
	}
	
	@GetMapping("/edittutor/{id}")
	public String showEditTutor(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		model.addAttribute("tutor",tutor);
		return "edit-tutor";
	}
	
	@PostMapping("/updatetutor")
	public String updatetutor(@RequestParam("id") String id, Tutor tutor,Model model) {
		restTemplate.put(tutorAPI + "/" + tutor.getId(), tutor);
		model.addAttribute("tutor",tutor);
		return "home";
	}
	
	@GetMapping("/appointment/{id}")
	public String showSession(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		List<Appointment> appointments = restTemplate.getForObject(appointmentAPI, List.class);
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Tutor> tutors = restTemplate.getForObject(tutorAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("appointments",appointments);
		model.addAttribute("subjects",subjects);
		model.addAttribute("tutors",tutors);
		model.addAttribute("students",students);
		return "appointment-list";
	}
	
	@GetMapping("/addappointment/{id}")
	public String showAddSession(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("subjects",subjects);
		model.addAttribute("students",students);
		return "add-appointment";
	}
	
	@PostMapping("addappointment")
	public String addAppointment(@RequestParam("tutorId") int tutorId, Appointment appointment, Model model) {
		//get highest id for appointment
		ResponseEntity<Appointment[]> response = restTemplate.getForEntity(appointmentAPI, Appointment[].class);
		Appointment[] members = response.getBody();
		int maxId = 1;
		for (Appointment existingMember : members) {
			if (existingMember.getId() > maxId) {
				maxId = existingMember.getId();
			}
		}
		int newId = maxId+1;
		appointment.setId(newId);
		
		//POST Appointment
		restTemplate.postForObject(appointmentAPI, appointment, Appointment.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Appointment> appointments = restTemplate.getForObject(appointmentAPI, List.class);
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Tutor> tutors = restTemplate.getForObject(tutorAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("appointments",appointments);
		model.addAttribute("subjects",subjects);
		model.addAttribute("tutors",tutors);
		model.addAttribute("students",students);
		return "appointment-list";
	}
	
	@PostMapping("/deleteappointment/{tutorId}/{appointmentId}")
	public String deleteappointment(@PathVariable("appointmentId") int appointmentId, @PathVariable("tutorId") int tutorId, Model model) {
		//DELETE Appointment
		restTemplate.delete(appointmentAPI+"/"+appointmentId);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Appointment> appointments = restTemplate.getForObject(appointmentAPI, List.class);
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Tutor> tutors = restTemplate.getForObject(tutorAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("appointments",appointments);
		model.addAttribute("subjects",subjects);
		model.addAttribute("tutors",tutors);
		model.addAttribute("students",students);
		return "appointment-list";
	}
	
	@GetMapping("/editappointment/{tutorId}/{appointmentId}")
	public String showEditAppointment(@PathVariable("appointmentId") int appointmentId, @PathVariable("tutorId") int tutorId, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		Appointment appointment = restTemplate.getForEntity(appointmentAPI + "/" + appointmentId, Appointment.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("appointment",appointment);
		model.addAttribute("students",students);
		model.addAttribute("subjects",subjects);
		return "edit-appointment";
	}
	
	@PostMapping("/updateappointment")
	public String updateAppointment(@RequestParam("tutorId") int tutorId, Appointment appointment,Model model) {
		restTemplate.postForObject(appointmentAPI, appointment, Appointment.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Appointment> appointments = restTemplate.getForObject(appointmentAPI, List.class);
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		List<Tutor> tutors = restTemplate.getForObject(tutorAPI, List.class);
		List<Student> students = restTemplate.getForObject(studentAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("appointments",appointments);
		model.addAttribute("subjects",subjects);
		model.addAttribute("tutors",tutors);
		model.addAttribute("students",students);
		return "appointment-list";
	}
	
	@GetMapping("/addsubject/{id}")
	public String showAddSubject(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		model.addAttribute("tutor",tutor);
		return "add-subject";
	}
	
	@PostMapping("addsubject")
	public String addSubject(@RequestParam("tutorId") int tutorId, Subject subject, Model model) {
		//get highest id for Subject
		ResponseEntity<Subject[]> response = restTemplate.getForEntity(subjectAPI, Subject[].class);
		Subject[] members = response.getBody();
		int maxId = 1;
		for (Subject existingMember : members) {
			if (existingMember.getId() > maxId) {
				maxId = existingMember.getId();
			}
		}
		int newId = maxId+1;
		subject.setId(newId);
		
		//POST Appointment
		restTemplate.postForObject(subjectAPI, subject, Subject.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("subjects",subjects);
		return "subject-list";
	}
	
	@GetMapping("/subject/{id}")
	public String showSubject(@PathVariable("id") String id, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + id, Tutor.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("subjects",subjects);
		return "subject-list";
	}
	
	@GetMapping("/editsubject/{tutorId}/{subjectId}")
	public String showEditSubject(@PathVariable("subjectId") int subjectId, @PathVariable("tutorId") int tutorId, Model model) {
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		Subject subject = restTemplate.getForEntity(subjectAPI + "/" + subjectId, Subject.class).getBody();
		model.addAttribute("tutor",tutor);
		model.addAttribute("subject",subject);
		return "edit-subject";
	}
	
	@PostMapping("/updatesubject")
	public String updateSubject(@RequestParam("tutorId") int tutorId, Subject subject,Model model) {
		restTemplate.postForObject(subjectAPI, subject, Subject.class);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("subjects",subjects);
		return "subject-list";
	}
	
	@PostMapping("/deletesubject/{tutorId}/{subjectId}")
	public String deletesubject(@PathVariable("subjectId") int subjectId, @PathVariable("tutorId") int tutorId, Model model) {
		//DELETE Appointment
		restTemplate.delete(subjectAPI+"/"+subjectId);
		Tutor tutor = restTemplate.getForEntity(tutorAPI + "/" + tutorId, Tutor.class).getBody();
		List<Subject> subjects = restTemplate.getForObject(subjectAPI, List.class);
		model.addAttribute("tutor",tutor);
		model.addAttribute("subjects",subjects);
		return "subject-list";
	}
}
