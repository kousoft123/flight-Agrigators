package com.kou.flight.adapter;

import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;
import java.util.List;
public interface FlightProviderAdapter {
    List<CanonicalFlight> searchFlights(SearchRequest request);
}