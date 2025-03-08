package com.travelplanner.repository;

import com.travelplanner.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDayId(Long dayId);
    List<Activity> findByDayIdOrderByStartTimeAsc(Long dayId);
    List<Activity> findByActivityType(String activityType);
} 