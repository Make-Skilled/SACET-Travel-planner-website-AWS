package com.travelplanner.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "avg_budget")
    private BigDecimal averageBudget;

    private String weather;

    @Column(columnDefinition = "TEXT")
    private String attractions;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Itinerary> itineraries;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripRecommendation> recommendations;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getAverageBudget() {
        return averageBudget;
    }

    public void setAverageBudget(BigDecimal averageBudget) {
        this.averageBudget = averageBudget;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<TripRecommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<TripRecommendation> recommendations) {
        this.recommendations = recommendations;
    }
}