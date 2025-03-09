package com.travelplanner.controller;

import com.travelplanner.model.Itinerary;
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
    public String listItineraries(Model model) {
        model.addAttribute("itineraries", itineraryService.getAllItineraries());
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
        return "itineraries/new";
    }

    @PostMapping
    public String createItinerary(@ModelAttribute Itinerary itinerary) {
        itineraryService.saveItinerary(itinerary);
        return "redirect:/itineraries";
    }

    @GetMapping("/{id}/edit")
    public String editItineraryForm(@PathVariable Long id, Model model) {
        model.addAttribute("itinerary", itineraryService.getItineraryById(id));
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "itineraries/edit";
    }

    @PostMapping("/{id}")
    public String updateItinerary(@PathVariable Long id, @ModelAttribute Itinerary itinerary) {
        itinerary.setId(id);
        itineraryService.saveItinerary(itinerary);
        return "redirect:/itineraries/" + id;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteItinerary(@PathVariable Long id) {
        itineraryService.deleteItinerary(id);
        return "success";
    }
}