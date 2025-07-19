package com.example.admission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionService {
    @Autowired ApplicationRepository apps;
    public void calculateMerit(Long appId) {
        Application a = apps.findById(appId).get();
        int marks = a.getStudent().getMarks();
        double merit = marks / 100.0 * 50 + Math.random() * 50;
        a.setMeritScore(merit);
        apps.save(a);
    }
    public void reviewAll() {
        for(var a : apps.findByStatus("PENDING")) {
            calculateMerit(a.getId());
            a.setStatus(a.getMeritScore() >= a.getCourse().getCutoff() ? "APPROVED" : "REJECTED");
            apps.save(a);
        }
    }
}

