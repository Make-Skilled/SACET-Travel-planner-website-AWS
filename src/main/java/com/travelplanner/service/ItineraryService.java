package com.travelplanner.service;

import com.travelplanner.model.Itinerary;
import com.travelplanner.model.Activity;
import java.util.List;

public interface ItineraryService {
    List<Itinerary> getAllItineraries();
    Itinerary getItineraryById(Long id);
    Itinerary saveItinerary(Itinerary itinerary);
    void deleteItinerary(Long id);
    List<Itinerary> findByDestination(Long destinationId);
    List<Itinerary> findByBudget(Double maxBudget);
    List<Itinerary> findByTravelMode(String travelMode);
    void addActivityToDay(Long itineraryId, Long dayNumber, Activity activity);
    void removeActivityFromDay(Long itineraryId, Long dayNumber, Long activityId);
} 