package com.travelplanner.controller;

import com.travelplanner.model.Destination;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "destinations/new";
    }

    @PostMapping
    public String createDestination(@ModelAttribute Destination destination, RedirectAttributes redirectAttributes) {
        try {
            if (destination.getName() == null || destination.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Destination name cannot be empty");
            }
            destinationService.saveDestination(destination);
            redirectAttributes.addFlashAttribute("message", "Destination created successfully!");
            return "redirect:/destinations";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/destinations/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String editDestinationForm(@PathVariable Long id, Model model) {
        model.addAttribute("destination", destinationService.getDestinationById(id));
        return "destinations/edit";
    }

    @PostMapping("/{id}")
    public String updateDestination(@PathVariable Long id, @ModelAttribute Destination destination, RedirectAttributes redirectAttributes) {
        try {
            if (destination.getName() == null || destination.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Destination name cannot be empty");
            }
            destination.setId(id);
            destinationService.saveDestination(destination);
            redirectAttributes.addFlashAttribute("message", "Destination updated successfully!");
            return "redirect:/destinations/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/destinations/" + id + "/edit";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Cannot delete destination as it is referenced by itineraries or recommendations");
        }
    }

    @GetMapping("/search")
    public String searchDestinations(@RequestParam String location, @RequestParam(required = false) String budget, Model model) {
        model.addAttribute("destinations", destinationService.searchDestinations(location, budget));
        return "destinations/list";
    }
}