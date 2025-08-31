package com.bitsandbites.Exception;

import java.time.LocalDateTime;

public class ErrorResponseDto {
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	
	private String message;

    public ErrorResponseDto(LocalDateTime timestamp, int status, String error, String message) {
        this.timeStamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
    
    // Getters and setters
    public LocalDateTime getTimestamp() 
    { 
    	return timeStamp; 
    }
    public int getStatus() { 
    	return status; 
    }
    public String getError() {
    	return error; 
    }
    public String getMessage() { 
    	return message; 
    }
}
//2. Create Error Response DTO
//Instead of Map, we’ll use a proper class for structured JSON error responses.