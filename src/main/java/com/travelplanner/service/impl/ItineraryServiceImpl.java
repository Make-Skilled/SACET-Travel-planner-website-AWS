package com.travelplanner.service.impl;

import com.travelplanner.model.Itinerary;
import com.travelplanner.model.ItineraryDay;
import com.travelplanner.model.Activity;
import com.travelplanner.repository.ItineraryRepository;
import com.travelplanner.repository.ActivityRepository;
import com.travelplanner.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    @Override
    public Itinerary getItineraryById(Long id) {
        return itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerary not found with id: " + id));
    }

    @Override
    @Transactional
    public Itinerary saveItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    @Override
    @Transactional
    public void deleteItinerary(Long id) {
        itineraryRepository.deleteById(id);
    }

    @Override
    public List<Itinerary> findByDestination(Long destinationId) {
        return itineraryRepository.findByDestinationId(destinationId);
    }

    @Override
    public List<Itinerary> findByBudget(Double maxBudget) {
        return itineraryRepository.findByTotalBudgetLessThanEqual(maxBudget);
    }

    @Override
    public List<Itinerary> findByTravelMode(String travelMode) {
        return itineraryRepository.findByTravelMode(travelMode);
    }

    @Override
    @Transactional
    public void addActivityToDay(Long itineraryId, Long dayNumber, Activity activity) {
        Itinerary itinerary = getItineraryById(itineraryId);
        ItineraryDay day = itinerary.getDays().stream()
                .filter(d -> d.getDayNumber().equals(dayNumber.intValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found in itinerary"));
        
        activity.setDay(day);
        day.addActivity(activity);
        itineraryRepository.save(itinerary);
    }

    @Override
    @Transactional
    public void removeActivityFromDay(Long itineraryId, Long dayNumber, Long activityId) {
        Itinerary itinerary = getItineraryById(itineraryId);
        ItineraryDay day = itinerary.getDays().stream()
                .filter(d -> d.getDayNumber().equals(dayNumber.intValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found in itinerary"));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        day.removeActivity(activity);
        activityRepository.delete(activity);
        itineraryRepository.save(itinerary);
    }
} 