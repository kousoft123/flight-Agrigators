package com.kou.flight.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class AggregationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AggregationException(String message) {
        super(message);
    }

    public AggregationException(String message, Throwable cause) {
        super(message, cause);
    }
}