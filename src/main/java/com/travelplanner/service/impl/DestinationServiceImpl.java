package com.travelplanner.service.impl;

import com.travelplanner.model.Destination;
import com.travelplanner.repository.DestinationRepository;
import com.travelplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }

    @Override
    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public List<Destination> searchDestinations(String location, String budget) {
        // Implement search logic based on location and budget
        // This is a simplified version - you might want to add more complex search criteria
        return destinationRepository.findByLocationContainingIgnoreCase(location);
    }
} 