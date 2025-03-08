package com.travelplanner.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "trip_recommendations")
public class TripRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @Column(name = "trip_type")
    private String tripType; // Adventure, Relaxation, Cultural, etc.

    @Column(name = "recommended_duration")
    private Integer recommendedDuration;

    @Column(name = "best_time_to_visit")
    private String bestTimeToVisit;

    @Column(name = "estimated_budget")
    private BigDecimal estimatedBudget;

    @Column(name = "suitable_for")
    private String suitableFor; // Family, Solo, Couples, etc.

    @Column(columnDefinition = "TEXT")
    private String highlights;

    @Column(name = "weather_conditions")
    private String weatherConditions;

    @Column(name = "local_events")
    private String localEvents;

    @Column(name = "travel_tips", columnDefinition = "TEXT")
    private String travelTips;

    private Integer rating;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public Integer getRecommendedDuration() {
        return recommendedDuration;
    }

    public void setRecommendedDuration(Integer recommendedDuration) {
        this.recommendedDuration = recommendedDuration;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public BigDecimal getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(BigDecimal estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public String getLocalEvents() {
        return localEvents;
    }

    public void setLocalEvents(String localEvents) {
        this.localEvents = localEvents;
    }

    public String getTravelTips() {
        return travelTips;
    }

    public void setTravelTips(String travelTips) {
        this.travelTips = travelTips;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
        lastUpdated = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDate.now();
    }
} 