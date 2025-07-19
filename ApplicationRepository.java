package com.example.admission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository  extends JpaRepository<Application, Long> {
	List<Application> findByStatus(String status);
}
