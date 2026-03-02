package com.example.springboot;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;


    // 🔹 Show Add Asset Form
    @GetMapping("/add")
    public String showForm(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("asset", new Asset());
        return "add-asset";
    }


    // 🔹 Save Asset
    @PostMapping("/save")
    public String saveAsset(@ModelAttribute Asset asset,
                            HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        assetService.saveAsset(asset);
        return "redirect:/dashboard";
    }


    // 🔹 Dashboard
    @GetMapping("/dashboard")
    public String viewDashboard(Model model,
                                HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("alerts", alertService.getAllAlerts());

        return "dashboard";
    }


    // 🔹 Delete Asset
    @GetMapping("/delete/{id}")
    public String deleteAsset(@PathVariable Long id,
                              HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        assetRepository.deleteById(id);
        return "redirect:/dashboard";
    }


    // 🔥 FIXED REAL-TIME SIMULATION
    @GetMapping("/simulate")
    @ResponseBody
    public String simulateData(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "Unauthorized";
        }

        List<Asset> assets = assetService.getAllAssets();

        if (assets.isEmpty()) {
            return "No assets found";
        }

        for (Asset asset : assets) {

            double newTemp = 50 + Math.random() * 50;
            double newVibration = 20 + Math.random() * 60;
            double newPressure = 10 + Math.random() * 90;

            asset.setTemperature(newTemp);
            asset.setVibration(newVibration);
            asset.setPressure(newPressure);

            assetService.saveAsset(asset);
        }

        System.out.println("Simulation triggered successfully");

        return "Simulation Updated";
    }


    // 🔥 Trend Page
    @GetMapping("/trend/{name}")
    public String viewTrend(@PathVariable String name,
                            Model model,
                            HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("history",
                assetHistoryRepository
                        .findByAssetNameOrderByTimestampAsc(name));

        model.addAttribute("assetName", name);

        return "trend";
    }
}