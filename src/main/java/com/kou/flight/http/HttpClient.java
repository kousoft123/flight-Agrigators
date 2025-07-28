package com.kou.flight.http;

import org.springframework.stereotype.Component;

@Component
public class HttpClient {
    public String get(String url) {
        return "{mocked_response}";
    }
}