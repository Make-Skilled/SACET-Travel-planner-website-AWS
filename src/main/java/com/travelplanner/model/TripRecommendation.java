package com.travelplanner.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip_recommendations")
public class TripRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_type", nullable = false)
    private String tripType;

    @Column(name = "suitable_for")
    private String suitableFor;

    @Column(name = "best_time_to_visit")
    private String bestTimeToVisit;

    @Column(columnDefinition = "TEXT")
    private String localEvents;

    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

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

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
    }

    public String getLocalEvents() {
        return localEvents;
    }

    public void setLocalEvents(String localEvents) {
        this.localEvents = localEvents;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
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