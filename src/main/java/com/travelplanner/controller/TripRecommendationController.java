package com.travelplanner.controller;

import com.travelplanner.model.TripRecommendation;
import com.travelplanner.service.TripRecommendationService;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recommendations")
public class TripRecommendationController {

    @Autowired
    private TripRecommendationService recommendationService;

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public String listRecommendations(Model model) {
        model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        return "recommendations/list";
    }

    @GetMapping("/{id}")
    public String viewRecommendation(@PathVariable Long id, Model model) {
        model.addAttribute("recommendation", recommendationService.getRecommendationById(id));
        return "recommendations/view";
    }

    @GetMapping("/new")
    public String newRecommendationForm(Model model) {
        model.addAttribute("recommendation", new TripRecommendation());
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "recommendations/form";
    }

    @PostMapping
    public String createRecommendation(@ModelAttribute TripRecommendation recommendation) {
        recommendationService.saveRecommendation(recommendation);
        return "redirect:/recommendations";
    }

    @GetMapping("/{id}/edit")
    public String editRecommendationForm(@PathVariable Long id, Model model) {
        model.addAttribute("recommendation", recommendationService.getRecommendationById(id));
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "recommendations/edit";
    }

    @PostMapping("/{id}")
    public String updateRecommendation(@PathVariable Long id, @ModelAttribute TripRecommendation recommendation) {
        recommendation.setId(id);
        recommendationService.saveRecommendation(recommendation);
        return "redirect:/recommendations/" + id;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return "success";
    }
}