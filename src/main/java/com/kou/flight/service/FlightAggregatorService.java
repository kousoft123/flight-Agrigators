package com.kou.flight.service;
import org.springframework.stereotype.Service;

import com.kou.flight.adapter.FlightProviderAdapter;
import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;

import java.util.Comparator;
import java.util.List;

@Service
public class FlightAggregatorService {
    private final List<FlightProviderAdapter> adapters;

    public FlightAggregatorService(List<FlightProviderAdapter> adapters) {
        this.adapters = adapters;
    }

    public List<CanonicalFlight> aggregate(SearchRequest request) {
        return adapters.stream()
                .flatMap(adapter -> adapter.searchFlights(request).stream())
                .sorted(Comparator.comparingDouble(CanonicalFlight::price))
                .toList();
    }
}
