package com.travelplanner.controller;

import com.travelplanner.model.Destination;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public String listDestinations(Model model) {
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "destinations/list";
    }

    @GetMapping("/{id}")
    public String viewDestination(@PathVariable Long id, Model model) {
        model.addAttribute("destination", destinationService.getDestinationById(id));
        return "destinations/view";
    }

    @GetMapping("/new")
    public String newDestinationForm(Model model) {
        model.addAttribute("destination", new Destination());
        return "destinations/form";
    }

    @PostMapping
    public String saveDestination(@ModelAttribute Destination destination) {
        destinationService.saveDestination(destination);
        return "redirect:/destinations";
    }
} 