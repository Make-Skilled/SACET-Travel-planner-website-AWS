package com.travelplanner.service.impl;

import com.travelplanner.model.TripRecommendation;
import com.travelplanner.repository.TripRecommendationRepository;
import com.travelplanner.service.TripRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
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
                .orElseThrow(() -> new RuntimeException("Recommendation not found with id: " + id));
    }

    @Override
    public TripRecommendation saveRecommendation(TripRecommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
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
    public List<TripRecommendation> findByMinRating(Integer minRating) {
        return recommendationRepository.findByRatingGreaterThanEqual(minRating);
    }
}