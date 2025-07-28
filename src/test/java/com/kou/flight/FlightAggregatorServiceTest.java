package com.kou.flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kou.flight.adapter.FlightProviderAdapter;
import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;
import com.kou.flight.service.FlightAggregatorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightAggregatorServiceTest {

    private FlightAggregatorService service;
    private FlightProviderAdapter providerA;
    private FlightProviderAdapter providerB;

    @BeforeEach
    void setUp() {
        providerA = mock(FlightProviderAdapter.class);
        providerB = mock(FlightProviderAdapter.class);
        service = new FlightAggregatorService(List.of(providerA, providerB));
    }

    @Test
    void testAggregateReturnsSortedFlights() {
        SearchRequest request = new SearchRequest("DEL", "BOM", "2025-08-01");

        CanonicalFlight flight1 = new CanonicalFlight("AirAlpha", "DEL", "BOM", 5000.0, "ProviderA", false, null, "Boeing 737");
        CanonicalFlight flight2 = new CanonicalFlight("JetBee", "DEL", "BOM", 5200.0, "ProviderB", false, null,	"JetBee 737");

		when(providerA.searchFlights(request)).thenReturn(List.of(flight1));
		when(providerB.searchFlights(request)).thenReturn(List.of(flight2));

        when(providerA.searchFlights(request)).thenReturn(List.of(flight1));
        when(providerB.searchFlights(request)).thenReturn(List.of(flight2));

        List<CanonicalFlight> result = service.aggregate(request);

        assertEquals(2, result.size());
        assertEquals("AirAlpha", result.get(0).airline());
        assertEquals("JetBee", result.get(1).airline());
    }

    @Test
    void testAggregateWithEmptyProviders() {
        SearchRequest request = new SearchRequest("DEL", "BOM", "2025-08-01");
        when(providerA.searchFlights(request)).thenReturn(List.of());
        when(providerB.searchFlights(request)).thenReturn(List.of());

        List<CanonicalFlight> result = service.aggregate(request);

        assertTrue(result.isEmpty());
    }
}