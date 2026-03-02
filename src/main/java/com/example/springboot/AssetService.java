package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;   // 🔥 NEW

    public Asset saveAsset(Asset asset) {

        double temp = asset.getTemperature();
        double vib = asset.getVibration();
        double pres = asset.getPressure();

        // =========================================
        // 🔥 STEP 1: Base Health Score Calculation
        // =========================================

        double score = 100;

        score -= temp * 0.3;
        score -= vib * 0.4;
        score -= pres * 0.3;

        if (pres < 20) {
            score -= 15;
        }

        if (score < 0) score = 0;

        asset.setHealthScore(score);

        // =========================================
        // 🔥 STEP 2: Status Classification
        // =========================================

        if (score < 40) {
            asset.setStatus("CRITICAL");
        } else if (score < 70) {
            asset.setStatus("WARNING");
        } else {
            asset.setStatus("HEALTHY");
        }

        // =========================================
        // 🔥 STEP 3: Risk Prediction
        // =========================================

        if (asset.getStatus().equals("CRITICAL")) {
            asset.setPredictedRisk("HIGH");
        } else if (asset.getStatus().equals("WARNING")) {
            asset.setPredictedRisk("MEDIUM");
        } else {
            asset.setPredictedRisk("LOW");
        }

        // =========================================
        // 🚨 STEP 4: Advanced Anomaly Detection
        // =========================================

        boolean anomaly = false;

        // Extreme thresholds
        if (temp > 85 || vib > 75 || pres > 95) {
            anomaly = true;
        }

        // Dangerous combination
        if (temp > 70 && vib > 60) {
            anomaly = true;
        }

        // Spike detection (compare with previous reading)
        List<Asset> allAssets = assetRepository.findAll();

        if (!allAssets.isEmpty()) {
            Asset previous = allAssets.get(allAssets.size() - 1);

            if (Math.abs(temp - previous.getTemperature()) > 30) {
                anomaly = true;
            }

            if (Math.abs(vib - previous.getVibration()) > 25) {
                anomaly = true;
            }

            if (Math.abs(pres - previous.getPressure()) > 35) {
                anomaly = true;
            }
        }

        // =========================================
        // 🚨 STEP 5: If anomaly detected → Override & Create Alert
        // =========================================

        if (anomaly) {

            asset.setStatus("CRITICAL");
            asset.setPredictedRisk("ANOMALY DETECTED");

            alertService.createAlert(
                    asset.getAssetName(),
                    "Critical anomaly detected due to abnormal sensor behavior",
                    "HIGH"
            );
        }

        // =========================================
        // 🔮 STEP 6: Failure Probability Calculation
        // =========================================

        double probability = 100 - asset.getHealthScore();

        if ("ANOMALY DETECTED".equals(asset.getPredictedRisk())) {
            probability += 20;
        }

        if (probability > 100) probability = 100;
        if (probability < 0) probability = 0;

        asset.setFailureProbability(probability);

        // =========================================
        // 📊 STEP 7: Save Asset History (NEW)
        // =========================================

        AssetHistory history = new AssetHistory(
                asset.getAssetName(),
                asset.getTemperature(),
                asset.getVibration(),
                asset.getPressure(),
                asset.getHealthScore()
        );

        assetHistoryRepository.save(history);

        // =========================================
        // 💾 Finally Save Asset
        // =========================================

        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}