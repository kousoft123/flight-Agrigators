package com.kou.flight.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SearchRequest(
    @NotBlank(message = "From location is required")
    String from,

    @NotBlank(message = "To location is required")
    String to,

    @NotBlank(message = "Date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in YYYY-MM-DD format")
    String date
) {}