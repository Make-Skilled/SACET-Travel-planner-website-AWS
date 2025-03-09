package com.travelplanner.service;

import com.travelplanner.model.TripRecommendation;
import java.util.List;

public interface TripRecommendationService {
    List<TripRecommendation> getAllRecommendations();
    TripRecommendation getRecommendationById(Long id);
    TripRecommendation saveRecommendation(TripRecommendation recommendation);
    void deleteRecommendation(Long id);
    List<TripRecommendation> findByTripType(String tripType);
    List<TripRecommendation> findBySuitableFor(String suitableFor);
    List<TripRecommendation> findByMinRating(Integer minRating);
}