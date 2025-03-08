package com.travelplanner.service;

import com.travelplanner.model.TripRecommendation;
import java.math.BigDecimal;
import java.util.List;

public interface TripRecommendationService {
    List<TripRecommendation> getAllRecommendations();
    TripRecommendation getRecommendationById(Long id);
    TripRecommendation saveRecommendation(TripRecommendation recommendation);
    void deleteRecommendation(Long id);
    List<TripRecommendation> findByDestination(Long destinationId);
    List<TripRecommendation> findByTripType(String tripType);
    List<TripRecommendation> findBySuitableFor(String suitableFor);
    List<TripRecommendation> findByMaxBudget(BigDecimal maxBudget);
    List<TripRecommendation> findByMaxDuration(Integer maxDuration);
    List<TripRecommendation> findByMinRating(Integer minRating);
    List<TripRecommendation> getPersonalizedRecommendations(String preferences, BigDecimal budget, Integer duration);
} 