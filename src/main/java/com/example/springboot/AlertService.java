package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public void createAlert(String assetName, String message, String severity) {
        Alert alert = new Alert(assetName, message, severity);
        alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}