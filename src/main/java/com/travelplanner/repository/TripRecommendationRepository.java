package com.travelplanner.repository;

import com.travelplanner.model.TripRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TripRecommendationRepository extends JpaRepository<TripRecommendation, Long> {
    List<TripRecommendation> findByDestinationId(Long destinationId);
    List<TripRecommendation> findByTripType(String tripType);
    List<TripRecommendation> findBySuitableFor(String suitableFor);
    List<TripRecommendation> findByEstimatedBudgetLessThanEqual(BigDecimal maxBudget);
    List<TripRecommendation> findByRecommendedDurationLessThanEqual(Integer maxDuration);
    List<TripRecommendation> findByRatingGreaterThanEqual(Integer minRating);
} 