package com.bitsandbites.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourceNotFoundException ex){
		ErrorResponseDto errorResponseDto=new ErrorResponseDto(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(), //eg)200,404,201 etc,value of http status code
				"Not Found",
				ex.getMessage()
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
				
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex){
		ErrorResponseDto errorResponseDto=new ErrorResponseDto(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error",
				ex.getMessage()
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
	}
//	
//	It handles any other unhandled exception in your project that is not specifically mapped in other @ExceptionHandler methods.
}
