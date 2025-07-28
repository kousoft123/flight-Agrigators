package com.kou.flight.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kou.flight.adapter.KongAirAdapter;
import com.kou.flight.dto.KongAirFlightDetails;
import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/kongair")
public class KongAirLiveController {
	private final KongAirAdapter kongAirAdapter;

	public KongAirLiveController(KongAirAdapter kongAirAdapter) {
		this.kongAirAdapter = kongAirAdapter;
	}

	@GetMapping("/flights")
	public ResponseEntity<List<CanonicalFlight>> getLiveFlights(
			@RequestParam String from,
			@RequestParam String to,
			@RequestParam String date
			) {
		SearchRequest request = new SearchRequest(from, to, date);
		return ResponseEntity.ok(kongAirAdapter.searchFlights(request));
	}

	@GetMapping("/flights/{flightNumber}")
	public ResponseEntity<CanonicalFlight> getFlightByNumber(@PathVariable String flightNumber) {
		return ResponseEntity.ok(kongAirAdapter.getFlightByNumber(flightNumber));
	}

	@GetMapping("/flights/{flightNumber}/details")
	public ResponseEntity<KongAirFlightDetails> getFlightDetails(@PathVariable String flightNumber) {
		return ResponseEntity.ok(kongAirAdapter.getFlightDetails(flightNumber));
	}

	@PostMapping("/search")
	public ResponseEntity<List<CanonicalFlight>> search(@Valid @RequestBody SearchRequest request) {
		return ResponseEntity.ok(kongAirAdapter.searchFlights(request));
	}
}