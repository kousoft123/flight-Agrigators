package com.kou.flight.adapter;
import org.springframework.stereotype.Component;

import com.kou.flight.dto.KongAirFlight;
import com.kou.flight.dto.KongAirFlightDetails;
import com.kou.flight.exception.AggregationException;
import com.kou.flight.http.HttpClientHelper;
import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;

import java.util.*;

@Component
public class KongAirAdapter implements FlightProviderAdapter {

    private final HttpClientHelper http;
    private static final String BASE_URL = "https://api.kong-air.com";

    public KongAirAdapter(HttpClientHelper http) {
        this.http = http;
    }

    @Override
    public List<CanonicalFlight> searchFlights(SearchRequest request) {
        try {
            Map<String, String> query = Map.of("date", request.date());
            KongAirFlight[] flights = http.get(BASE_URL, "/flights", query, KongAirFlight[].class);

            if (flights == null) return List.of();

            List<CanonicalFlight> results = new ArrayList<>();

            for (KongAirFlight flight : flights) {
                if (!flight.route_id().equals(request.from() + "-" + request.to())) continue;

                KongAirFlightDetails details = null;
                try {
                    details = http.get(BASE_URL, "/flights/" + flight.number() + "/details", Map.of(), KongAirFlightDetails.class);
                } catch (Exception e) {
                    System.err.printf("Details not found for flight %s: %s%n", flight.number(), e.getMessage());
                }

                results.add(new CanonicalFlight(
                    "KongAir",
                    flight.scheduled_departure().toString(),
                    flight.scheduled_arrival().toString(),
                    0.0,
                    "KongAir",
                    details != null && details.in_flight_entertainment(),
                    details != null ? details.meal_options() : List.of(),
                    details != null ? details.aircraft_type() : "Unknown"
                ));
            }

            return results;

        } catch (Exception e) {
            throw new AggregationException("Failed to fetch flights from KongAir", e);
        }
    }
    
    public CanonicalFlight getFlightByNumber(String flightNumber) {
        try {
            // Fetch core flight data
            KongAirFlight flight = http.get(BASE_URL, "/flights/" + flightNumber, Map.of(), KongAirFlight.class);
            if (flight == null) {
                throw new AggregationException("Flight not found: " + flightNumber);
            }

            // Fetch flight details
            KongAirFlightDetails details = null;
            try {
                details = http.get(BASE_URL, "/flights/" + flightNumber + "/details", Map.of(), KongAirFlightDetails.class);
            } catch (Exception e) {
                System.err.printf("No details for flight %s: %s%n", flightNumber, e.getMessage());
            }

            return new CanonicalFlight(
                "KongAir",
                flight.scheduled_departure().toString(),
                flight.scheduled_arrival().toString(),
                0.0,
                "KongAir",
                details != null && details.in_flight_entertainment(),
                details != null ? details.meal_options() : List.of(),
                details != null ? details.aircraft_type() : "Unknown"
            );

        } catch (Exception e) {
            throw new AggregationException("Error retrieving flight " + flightNumber, e);
        }
    }
    
    public KongAirFlightDetails getFlightDetails(String flightNumber) {
        try {
            KongAirFlightDetails details = http.get(
                BASE_URL,
                "/flights/" + flightNumber + "/details",
                Map.of(),
                KongAirFlightDetails.class
            );

            if (details == null) {
                throw new AggregationException("No flight details found for: " + flightNumber);
            }

            return details;

        } catch (Exception e) {
            throw new AggregationException("Error retrieving flight details for " + flightNumber, e);
        }
    }
}
