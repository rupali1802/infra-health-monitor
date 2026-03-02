package com.example.springboot;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;
    private String message;
    private String severity;
    private LocalDateTime timestamp;

    public Alert() {}

    public Alert(String assetName, String message, String severity) {
        this.assetName = assetName;
        this.message = message;
        this.severity = severity;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}