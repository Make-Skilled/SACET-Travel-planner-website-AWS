package com.travelplanner.service.impl;

import com.travelplanner.model.TripRecommendation;
import com.travelplanner.repository.TripRecommendationRepository;
import com.travelplanner.service.TripRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripRecommendationServiceImpl implements TripRecommendationService {

    @Autowired
    private TripRecommendationRepository recommendationRepository;

    @Override
    public List<TripRecommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public TripRecommendation getRecommendationById(Long id) {
        return recommendationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip recommendation not found with id: " + id));
    }

    @Override
    @Transactional
    public TripRecommendation saveRecommendation(TripRecommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    @Transactional
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }

    @Override
    public List<TripRecommendation> findByDestination(Long destinationId) {
        return recommendationRepository.findByDestinationId(destinationId);
    }

    @Override
    public List<TripRecommendation> findByTripType(String tripType) {
        return recommendationRepository.findByTripType(tripType);
    }

    @Override
    public List<TripRecommendation> findBySuitableFor(String suitableFor) {
        return recommendationRepository.findBySuitableFor(suitableFor);
    }

    @Override
    public List<TripRecommendation> findByMaxBudget(BigDecimal maxBudget) {
        return recommendationRepository.findByEstimatedBudgetLessThanEqual(maxBudget);
    }

    @Override
    public List<TripRecommendation> findByMaxDuration(Integer maxDuration) {
        return recommendationRepository.findByRecommendedDurationLessThanEqual(maxDuration);
    }

    @Override
    public List<TripRecommendation> findByMinRating(Integer minRating) {
        return recommendationRepository.findByRatingGreaterThanEqual(minRating);
    }

    @Override
    public List<TripRecommendation> getPersonalizedRecommendations(String preferences, BigDecimal budget, Integer duration) {
        // Get all recommendations within budget and duration constraints
        List<TripRecommendation> baseRecommendations = recommendationRepository.findAll().stream()
                .filter(r -> r.getEstimatedBudget().compareTo(budget) <= 0)
                .filter(r -> r.getRecommendedDuration() <= duration)
                .collect(Collectors.toList());

        // If preferences are provided, filter and sort by relevance
        if (preferences != null && !preferences.trim().isEmpty()) {
            String[] preferenceTerms = preferences.toLowerCase().split(",");
            
            return baseRecommendations.stream()
                    .filter(r -> matchesPreferences(r, preferenceTerms))
                    .sorted((r1, r2) -> {
                        int r1Score = calculateRelevanceScore(r1, preferenceTerms);
                        int r2Score = calculateRelevanceScore(r2, preferenceTerms);
                        return r2Score - r1Score; // Sort in descending order
                    })
                    .collect(Collectors.toList());
        }

        return baseRecommendations;
    }

    private boolean matchesPreferences(TripRecommendation recommendation, String[] preferenceTerms) {
        String content = String.join(" ", 
            recommendation.getTripType().toLowerCase(),
            recommendation.getSuitableFor().toLowerCase(),
            recommendation.getHighlights().toLowerCase(),
            recommendation.getTravelTips().toLowerCase()
        );

        for (String term : preferenceTerms) {
            if (content.contains(term.trim())) {
                return true;
            }
        }
        return false;
    }

    private int calculateRelevanceScore(TripRecommendation recommendation, String[] preferenceTerms) {
        int score = 0;
        String content = String.join(" ", 
            recommendation.getTripType().toLowerCase(),
            recommendation.getSuitableFor().toLowerCase(),
            recommendation.getHighlights().toLowerCase(),
            recommendation.getTravelTips().toLowerCase()
        );

        for (String term : preferenceTerms) {
            if (content.contains(term.trim())) {
                score++;
            }
        }

        // Add rating bonus
        if (recommendation.getRating() != null) {
            score += recommendation.getRating();
        }

        return score;
    }
} 