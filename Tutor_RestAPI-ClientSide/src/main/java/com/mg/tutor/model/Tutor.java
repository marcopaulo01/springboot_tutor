package com.mg.tutor.model;

public class Tutor {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String bio;
	private String subjectExpertise;
	
	public Tutor() {
		
	}
	
	public Tutor(int id, String name, String email, String phone, String bio, String subjectExpertise) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.bio = bio;
		this.subjectExpertise = subjectExpertise;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getSubjectExpertise() {
		return subjectExpertise;
	}
	public void setSubjectExpertise(String subjectExpertise) {
		this.subjectExpertise = subjectExpertise;
	}
}
