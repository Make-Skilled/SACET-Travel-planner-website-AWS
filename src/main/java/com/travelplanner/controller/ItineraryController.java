package com.travelplanner.controller;

import com.travelplanner.model.Itinerary;
import com.travelplanner.model.Activity;
import com.travelplanner.service.ItineraryService;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public String listItineraries(
            @RequestParam(required = false) Long destinationId,
            @RequestParam(required = false) Double maxBudget,
            @RequestParam(required = false) String travelMode,
            Model model) {
        
        if (destinationId != null) {
            model.addAttribute("itineraries", itineraryService.findByDestination(destinationId));
        } else if (maxBudget != null) {
            model.addAttribute("itineraries", itineraryService.findByBudget(maxBudget));
        } else if (travelMode != null) {
            model.addAttribute("itineraries", itineraryService.findByTravelMode(travelMode));
        } else {
            model.addAttribute("itineraries", itineraryService.getAllItineraries());
        }
        
        return "itineraries/list";
    }

    @GetMapping("/{id}")
    public String viewItinerary(@PathVariable Long id, Model model) {
        model.addAttribute("itinerary", itineraryService.getItineraryById(id));
        return "itineraries/view";
    }

    @GetMapping("/new")
    public String newItineraryForm(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "itineraries/form";
    }

    @GetMapping("/{id}/edit")
    public String editItineraryForm(@PathVariable Long id, Model model) {
        model.addAttribute("itinerary", itineraryService.getItineraryById(id));
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "itineraries/form";
    }

    @PostMapping
    public String saveItinerary(@ModelAttribute Itinerary itinerary) {
        itineraryService.saveItinerary(itinerary);
        return "redirect:/itineraries";
    }

    @PostMapping("/{id}/delete")
    public String deleteItinerary(@PathVariable Long id) {
        itineraryService.deleteItinerary(id);
        return "redirect:/itineraries";
    }

    @PostMapping("/{itineraryId}/days/{dayNumber}/activities")
    public String addActivity(
            @PathVariable Long itineraryId,
            @PathVariable Long dayNumber,
            @ModelAttribute Activity activity) {
        itineraryService.addActivityToDay(itineraryId, dayNumber, activity);
        return "redirect:/itineraries/" + itineraryId;
    }

    @PostMapping("/{itineraryId}/days/{dayNumber}/activities/{activityId}/delete")
    public String removeActivity(
            @PathVariable Long itineraryId,
            @PathVariable Long dayNumber,
            @PathVariable Long activityId) {
        itineraryService.removeActivityFromDay(itineraryId, dayNumber, activityId);
        return "redirect:/itineraries/" + itineraryId;
    }
} 