SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS trip_recommendations;
DROP TABLE IF EXISTS itinerary_days;
DROP TABLE IF EXISTS itineraries;
DROP TABLE IF EXISTS destinations;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE destinations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description TEXT,
    weather VARCHAR(255),
    attractions TEXT,
    avg_budget DECIMAL(10,2)
);

CREATE TABLE trip_recommendations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    destination_id BIGINT,
    trip_type VARCHAR(50),
    suitable_for VARCHAR(50),
    estimated_budget DECIMAL(10,2),
    recommended_duration INTEGER,
    rating DECIMAL(3,1),
    best_time_to_visit VARCHAR(255),
    weather_conditions VARCHAR(255),
    highlights TEXT,
    travel_tips TEXT,
    local_events TEXT,
    created_date TIMESTAMP,
    last_updated TIMESTAMP,
    FOREIGN KEY (destination_id) REFERENCES destinations(id)
);

CREATE TABLE itineraries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    destination_id BIGINT,
    title VARCHAR(255),
    description TEXT,
    start_date DATE,
    end_date DATE,
    total_budget DECIMAL(10,2),
    travel_mode VARCHAR(50),
    preferences TEXT,
    FOREIGN KEY (destination_id) REFERENCES destinations(id)
);

CREATE TABLE itinerary_days (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    itinerary_id BIGINT,
    day_number INT,
    activities TEXT,
    accommodation VARCHAR(255),
    FOREIGN KEY (itinerary_id) REFERENCES itineraries(id)
); 