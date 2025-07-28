package com.kou.flight.dto;
import java.util.List;

public record KongAirFlightDetails(
    String flight_number,
    boolean in_flight_entertainment,
    List<String> meal_options,
    String aircraft_type
) {}