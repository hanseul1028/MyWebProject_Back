package com.kh.myweb.auth.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kh.myweb.auth.service.AuthService;
import com.kh.myweb.auth.token.model.service.TokenService;
import com.kh.myweb.member.model.dto.MemberDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;
	private final TokenService tokenService;
 
	// 생성자 주입 (필수!)
	public AuthController(AuthService authService, TokenService tokenService) {
		this.authService = authService;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberDTO member){
		System.out.println("로그인 요청 DTO: " + member);
		Map<String, String> loginResponse = authService.login(member);
		log.info("토큰 확인{}" , loginResponse);
		return ResponseEntity.ok(loginResponse);
		//return ResponseEntity.ok(authService.login(member));
	}
}
