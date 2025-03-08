package com.travelplanner.controller;

import com.travelplanner.model.TripRecommendation;
import com.travelplanner.service.TripRecommendationService;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/recommendations")
public class TripRecommendationController {

    @Autowired
    private TripRecommendationService recommendationService;

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public String listRecommendations(
            @RequestParam(required = false) String tripType,
            @RequestParam(required = false) String suitableFor,
            @RequestParam(required = false) BigDecimal maxBudget,
            @RequestParam(required = false) Integer maxDuration,
            @RequestParam(required = false) Integer minRating,
            Model model) {
        
        if (tripType != null) {
            model.addAttribute("recommendations", recommendationService.findByTripType(tripType));
        } else if (suitableFor != null) {
            model.addAttribute("recommendations", recommendationService.findBySuitableFor(suitableFor));
        } else if (maxBudget != null) {
            model.addAttribute("recommendations", recommendationService.findByMaxBudget(maxBudget));
        } else if (maxDuration != null) {
            model.addAttribute("recommendations", recommendationService.findByMaxDuration(maxDuration));
        } else if (minRating != null) {
            model.addAttribute("recommendations", recommendationService.findByMinRating(minRating));
        } else {
            model.addAttribute("recommendations", recommendationService.getAllRecommendations());
        }

        return "recommendations/list";
    }

    @GetMapping("/personalized")
    public String getPersonalizedRecommendations(
            @RequestParam String preferences,
            @RequestParam BigDecimal budget,
            @RequestParam Integer duration,
            Model model) {
        
        model.addAttribute("recommendations", 
            recommendationService.getPersonalizedRecommendations(preferences, budget, duration));
        model.addAttribute("isPersonalized", true);
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

    @GetMapping("/{id}/edit")
    public String editRecommendationForm(@PathVariable Long id, Model model) {
        model.addAttribute("recommendation", recommendationService.getRecommendationById(id));
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "recommendations/form";
    }

    @PostMapping
    public String saveRecommendation(@ModelAttribute TripRecommendation recommendation) {
        recommendationService.saveRecommendation(recommendation);
        return "redirect:/recommendations";
    }

    @PostMapping("/{id}/delete")
    public String deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return "redirect:/recommendations";
    }
} 