package com.travelplanner.repository;

import com.travelplanner.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByDestinationId(Long destinationId);
    List<Itinerary> findByTotalBudgetLessThanEqual(Double maxBudget);
    List<Itinerary> findByTravelMode(String travelMode);
} 