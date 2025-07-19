package com.example.admission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
    @Id 
    @GeneratedValue private Long id;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCutoff() {
		return cutoff;
	}
	public void setCutoff(Integer cutoff) {
		this.cutoff = cutoff;
	}
	private String name;
    private Integer cutoff;
    // getters/setters
}
