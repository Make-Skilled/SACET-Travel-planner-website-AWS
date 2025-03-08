-- Clear existing data
DELETE FROM itinerary_days;
DELETE FROM trip_recommendations;
DELETE FROM itineraries;
DELETE FROM destinations;

-- Insert destinations first
INSERT INTO destinations (name, location, description, weather, attractions, avg_budget)
VALUES 
('Goa', 'India', 'Beautiful beaches and vibrant culture', 'Tropical', 'Beaches, Churches, Nightlife', 1200),
('Bangkok', 'Thailand', 'Bustling city with rich culture', 'Tropical', 'Temples, Markets, Street Food', 1500),
('Rome', 'Italy', 'Historic city with ancient ruins', 'Mediterranean', 'Colosseum, Vatican, Roman Forum', 2200),
('Sydney', 'Australia', 'Coastal city with iconic landmarks', 'Temperate', 'Opera House, Harbour Bridge, Bondi Beach', 2500),
('Cairo', 'Egypt', 'Ancient city with pyramids', 'Desert', 'Pyramids, Nile, Museums', 1800);

-- Insert trip recommendations
INSERT INTO trip_recommendations 
(destination_id, trip_type, suitable_for, estimated_budget, recommended_duration, rating, 
best_time_to_visit, weather_conditions, highlights, travel_tips, local_events, created_date, last_updated)
VALUES 
(1, 'BEACH_HOLIDAY', 'FRIENDS', 1200, 5, 4.5, 'October to March', 'Pleasant winter season',
'Beach hopping, Water sports, Old Goa churches', 
'Rent a bike, try local seafood, visit spice plantations',
'Goa Carnival, Sunburn Festival', 
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(2, 'CULTURAL', 'FAMILY', 1500, 4, 4.6, 'November to February', 'Cool and dry season',
'Temple tours, River cruise, Street food exploration',
'Use BTS Skytrain, respect temple dress codes, carry water',
'Songkran Festival, Loy Krathong',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(3, 'HISTORICAL', 'COUPLES', 2200, 6, 4.9, 'April to June', 'Spring weather',
'Ancient Rome tour, Vatican Museums, Roman Forum',
'Buy Roma Pass, book skip-the-line tickets, comfortable shoes',
'Rome Film Festival, Opera at Caracalla',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(4, 'URBAN_ADVENTURE', 'FAMILY', 2500, 7, 4.7, 'September to November', 'Spring season',
'Harbor cruise, Coastal walks, Wildlife encounters',
'Get Opal card, book Opera House tours early, sunscreen essential',
'Vivid Sydney, New Years Eve fireworks',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(5, 'HISTORICAL', 'SOLO', 1800, 5, 4.8, 'October to April', 'Mild winter weather',
'Pyramid tours, Nile cruise, Khan el-Khalili bazaar',
'Book guided tours, drink bottled water, respect local customs',
'Abu Simbel Sun Festival, Cairo Film Festival',
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert itineraries
INSERT INTO itineraries 
(destination_id, title, description, start_date, end_date, total_budget, travel_mode, preferences)
VALUES
(1, 'Goa Beach Week', 'Beach hopping and cultural exploration in Goa',
'2024-02-15', '2024-02-20', 1200, 'FLIGHT', 'Beach resorts, water sports, nightlife'),

(2, 'Bangkok Discovery', 'Family exploration of Thai culture and cuisine',
'2024-03-10', '2024-03-14', 1500, 'FLIGHT', 'Family-friendly hotels, cultural activities'),

(3, 'Roman Holiday', 'Romantic journey through ancient Rome',
'2024-05-01', '2024-05-07', 2200, 'FLIGHT', 'Boutique hotels, romantic dining'),

(4, 'Sydney Adventure', 'Family fun in Sydney and surroundings',
'2024-09-15', '2024-09-22', 2500, 'FLIGHT', 'Harbor views, family activities'),

(5, 'Cairo Explorer', 'Solo adventure through ancient Egypt',
'2024-11-01', '2024-11-06', 1800, 'FLIGHT', 'Historical tours, local experiences');

-- Insert itinerary days
INSERT INTO itinerary_days 
(itinerary_id, day_number, activities, accommodation)
VALUES
(1, 1, 'Arrive in Goa, Check-in, Beach visit', 'Beach Resort'),
(1, 2, 'Water sports, Beach hopping', 'Beach Resort'),
(1, 3, 'Old Goa churches tour', 'Beach Resort'),
(1, 4, 'Spice plantation visit, Local market', 'Beach Resort'),
(1, 5, 'Free day at beach, Departure', 'Beach Resort'),

(2, 1, 'Arrive in Bangkok, Temple visits', 'City Hotel'),
(2, 2, 'Floating market tour', 'City Hotel'),
(2, 3, 'Grand Palace visit', 'City Hotel'),
(2, 4, 'Shopping, Departure', 'City Hotel'),

(3, 1, 'Arrive in Rome, Colosseum tour', 'Boutique Hotel'),
(3, 2, 'Vatican Museums, St. Peters Basilica', 'Boutique Hotel'),
(3, 3, 'Roman Forum, Palatine Hill', 'Boutique Hotel'),
(3, 4, 'Trevi Fountain, Spanish Steps', 'Boutique Hotel'),
(3, 5, 'Pantheon, Local exploration', 'Boutique Hotel'),
(3, 6, 'Shopping, Departure', 'Boutique Hotel'),

(4, 1, 'Arrive in Sydney, Opera House tour', 'Harbor Hotel'),
(4, 2, 'Bondi Beach, Coastal walk', 'Harbor Hotel'),
(4, 3, 'Taronga Zoo, Ferry ride', 'Harbor Hotel'),
(4, 4, 'Blue Mountains day trip', 'Harbor Hotel'),
(4, 5, 'Sydney Harbor Bridge climb', 'Harbor Hotel'),
(4, 6, 'Manly Beach day', 'Harbor Hotel'),
(4, 7, 'Shopping, Departure', 'Harbor Hotel'),

(5, 1, 'Arrive in Cairo, Pyramids tour', 'City Hotel'),
(5, 2, 'Egyptian Museum, Khan el-Khalili', 'City Hotel'),
(5, 3, 'Nile dinner cruise', 'City Hotel'),
(5, 4, 'Saqqara and Memphis tour', 'City Hotel'),
(5, 5, 'Islamic Cairo tour, Departure', 'City Hotel'); 