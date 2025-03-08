package com.travelplanner.service;

import com.travelplanner.model.Destination;
import java.util.List;

public interface DestinationService {
    List<Destination> getAllDestinations();
    Destination getDestinationById(Long id);
    Destination saveDestination(Destination destination);
    void deleteDestination(Long id);
    List<Destination> searchDestinations(String location, String budget);
} 