package com.velocity.anjana;

public class Student {
	
	private String id;
	private String name;
	private int score;
	private char grade;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public Student(String id, String name, int score, char grade) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.grade = grade;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	

}
