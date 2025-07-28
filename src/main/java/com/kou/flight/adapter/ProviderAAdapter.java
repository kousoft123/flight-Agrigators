package com.kou.flight.adapter;

import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ProviderAAdapter implements FlightProviderAdapter {
    @Override
    public List<CanonicalFlight> searchFlights(SearchRequest request) {
		return List.of(
				new CanonicalFlight("AirFly", request.from(), request.to(), 5000.0, "ProviderA", true,
						List.of("Vegetarian", "Non-Vegetarian"), "Boeing 737"),
				new CanonicalFlight("SkyWings", request.from(), request.to(), 4800.0, "ProviderA", false,
						List.of("Vegan", "Gluten-Free"), "Airbus A320"));
    }
}