package com.kou.flight.model;

import java.util.List;

public record CanonicalFlight(String airline, String departure, String arrival, double price, String provider,
		                                boolean inFlightEntertainment, List<String> mealOptions, String aircraftTypeString
		
		
		
		) {}

