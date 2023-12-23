package com.mg.tutor.student;

public class Student {

	private int id;
	private String name;
	private String email;
	private String phone;
	private int grade;
	
	public Student(int id, String name, String email, String phone, int grade) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.grade = grade;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
