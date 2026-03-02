package com.example.springboot;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;
    private double temperature;
    private double vibration;
    private double pressure;
    private double healthScore;
    private LocalDateTime timestamp;

    public AssetHistory() {}

    public AssetHistory(String assetName, double temperature,
                        double vibration, double pressure,
                        double healthScore) {

        this.assetName = assetName;
        this.temperature = temperature;
        this.vibration = vibration;
        this.pressure = pressure;
        this.healthScore = healthScore;
        this.timestamp = LocalDateTime.now();
    }

    // getters
    public String getAssetName() { return assetName; }
    public double getTemperature() { return temperature; }
    public double getVibration() { return vibration; }
    public double getPressure() { return pressure; }
    public double getHealthScore() { return healthScore; }
    public LocalDateTime getTimestamp() { return timestamp; }
}