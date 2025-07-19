package com.example.admission;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdmissionController {
	@Autowired
	StudentRepository students;
	@Autowired
	CourseRepository courses;
	@Autowired
	ApplicationRepository apps;
	@Autowired
	AdmissionService service;

	@PostMapping("/students")
	public Student addStudent(@RequestBody Student s) {
		return students.save(s);
	}
	
	 @GetMapping("/courses")
	    public List<Course> getAllCourses() {
	        return courses.findAll();
	    }
	
	@PostMapping("/get-courses")
	public Course addCourse(@RequestBody Course c) {
		return courses.save(c);
	}

	@PostMapping("/apply")
	public Application apply(@RequestParam Long sid, @RequestParam Long cid) {
		Application a = new Application();
		a.setStudent(students.findById(sid).get());
		a.setCourse(courses.findById(cid).get());
		a.setStatus("PENDING");
		return apps.save(a);
	}

	@PostMapping("/admin/review")
	public String review() {
		service.reviewAll();
		return "Reviewed all pending apps.";
	}

	@GetMapping("/applications")
	public List<Application> allApps() {
		return apps.findAll();
	}

	// Export as CSV
	@GetMapping("/export/csv")
	public void exportCsv(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/csv");
		resp.setHeader("Content-Disposition", "attachment; filename=admissions.csv");
		PrintWriter w = resp.getWriter();
		w.println("appId,student,course,merit,status");
		for (var a : apps.findAll()) {
			w.printf("%d,%s,%s,%.2f,%s\n", a.getId(), a.getStudent().getName(), a.getCourse().getName(),
					a.getMeritScore(), a.getStatus());
		}
	}
}
