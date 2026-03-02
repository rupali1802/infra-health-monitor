package com.example.springboot;

import jakarta.persistence.*;

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;
    private String location;
    private double temperature;
    private double vibration;
    private double pressure;
    private String status;

    // 🔥 Advanced Fields
    private double healthScore;
    private String predictedRisk;
    private double failureProbability;   // 🔮 NEW FIELD


    public Asset() {}

    public Asset(String assetName, String location,
                 double temperature, double vibration,
                 double pressure) {
        this.assetName = assetName;
        this.location = location;
        this.temperature = temperature;
        this.vibration = vibration;
        this.pressure = pressure;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getVibration() {
        return vibration;
    }

    public void setVibration(double vibration) {
        this.vibration = vibration;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }

    public String getPredictedRisk() {
        return predictedRisk;
    }

    public void setPredictedRisk(String predictedRisk) {
        this.predictedRisk = predictedRisk;
    }

    // 🔮 Failure Probability

    public double getFailureProbability() {
        return failureProbability;
    }

    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }
}