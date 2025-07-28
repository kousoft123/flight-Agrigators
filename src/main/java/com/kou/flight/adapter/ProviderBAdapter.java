package com.kou.flight.adapter;
import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ProviderBAdapter implements FlightProviderAdapter {
    @Override
    public List<CanonicalFlight> searchFlights(SearchRequest request) {
		return List.of(
				new CanonicalFlight("JetStream", request.from(), request.to(), 5200.0, "ProviderB", true,
						List.of("Vegetarian", "Non-Vegetarian"), "Boeing 777"),
				new CanonicalFlight("CloudAir", request.from(), request.to(), 5300.0, "ProviderB", false,
						List.of("Vegan", "Gluten-Free"), "Airbus A330"));
    }
}