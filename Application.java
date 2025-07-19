package com.example.admission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Application {
    @Id @GeneratedValue private Long id;
    @ManyToOne Student student;
    @ManyToOne Course course;
    private String status; // PENDING, APPROVED, REJECTED
    private Double meritScore;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getMeritScore() {
		return meritScore;
	}
	public void setMeritScore(Double meritScore) {
		this.meritScore = meritScore;
	}
   
}

