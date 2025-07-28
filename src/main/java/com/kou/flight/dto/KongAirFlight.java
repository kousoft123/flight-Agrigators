package com.kou.flight.dto;
import java.time.ZonedDateTime;

public record KongAirFlight(
    String number,
    String route_id,
    ZonedDateTime scheduled_departure,
    ZonedDateTime scheduled_arrival
) {}