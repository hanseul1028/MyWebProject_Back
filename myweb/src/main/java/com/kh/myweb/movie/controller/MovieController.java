package com.kh.myweb.movie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myweb.movie.model.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value="movies", produces="application/json; charset = UTF-8")
@RequiredArgsConstructor
public class MovieController {
	
	
	private final MovieService movieService;

	
	@GetMapping
	public ResponseEntity<String> getMovie() {
		String responseData = movieService.requestGetMovie(); 
		return ResponseEntity.ok(responseData);
	}
	
	
	
	
	
	
	
}































