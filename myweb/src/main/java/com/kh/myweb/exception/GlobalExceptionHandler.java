package com.kh.myweb.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//중복제거 
	private ResponseEntity<Map<String, String>> makeResponseEntity(RuntimeException e, HttpStatus status){
		Map<String, String> error = new HashMap<>();
		error.put("error-message", e.getMessage());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(InvalidUserRequestException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserError(InvalidUserRequestException e){
		return makeResponseEntity(e, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MemberIdDuplicateException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateMemberId(MemberIdDuplicateException e){
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateMemberId(CustomAuthenticationException e){
		return makeResponseEntity(e, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentsNotValid(MethodArgumentNotValidException e){
		Map<String, String> errors = new HashMap();
		
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}
}