package com.mg.tutor.model;

public class Appointment {

	private int id;
	private String date;
	private String startTime;
	private String endTime;
	private String location;
	private String notes;
	private int subjectId;
	private int tutorId;
	private int studentId;
	
	public Appointment() {
		
	}
	
	public Appointment(int id, String date, String startTime, String endTime, String location, String notes, int subjectId,
			int tutorId, int studentId) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.notes = notes;
		this.subjectId = subjectId;
		this.tutorId = tutorId;
		this.studentId = studentId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getTutorId() {
		return tutorId;
	}
	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
