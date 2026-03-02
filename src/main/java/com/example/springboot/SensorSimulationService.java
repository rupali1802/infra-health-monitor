package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SensorSimulationService {

    @Autowired
    private AssetService assetService;

    private Random random = new Random();

    // Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void generateSensorData() {

        Asset asset = new Asset();

        asset.setAssetName("Bridge-" + (random.nextInt(3) + 1));
        asset.setLocation("Zone-" + (random.nextInt(5) + 1));

        // Generate realistic sensor values
        double temperature = 20 + random.nextDouble() * 90;
        double vibration = 10 + random.nextDouble() * 80;
        double pressure = 30 + random.nextDouble() * 70;

        asset.setTemperature(temperature);
        asset.setVibration(vibration);
        asset.setPressure(pressure);

        assetService.saveAsset(asset);

        System.out.println("Simulated Data Saved: " + asset.getAssetName());
    }
}