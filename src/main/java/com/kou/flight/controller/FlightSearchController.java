package com.kou.flight.controller;

import com.kou.flight.model.CanonicalFlight;
import com.kou.flight.model.SearchRequest;
import com.kou.flight.service.FlightAggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {
    private final FlightAggregatorService service;

    public FlightSearchController(FlightAggregatorService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<List<CanonicalFlight>> search(@RequestBody @Valid SearchRequest request) {
        return ResponseEntity.ok(service.aggregate(request));
    }
}