package com.kh.myweb.member.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myweb.member.model.dto.MemberDTO;
import com.kh.myweb.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	// 회원가입 
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid MemberDTO member){
		System.out.println("회원가입 요청 들어옴: " + member);
		memberService.signUp(member);
		
		return ResponseEntity.ok().build();
	}
	
	

}















