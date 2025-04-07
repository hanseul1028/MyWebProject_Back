package com.kh.myweb.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import com.kh.myweb.auth.token.model.service.TokenService;

import org.springframework.stereotype.Service;

import com.kh.myweb.auth.model.vo.CustomUserDetails;
import com.kh.myweb.exception.CustomAuthenticationException;
import com.kh.myweb.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final AuthenticationManager authenticationManager;
//	private final JwtUtil jwtUtil;
	private final TokenService tokenService;
	
	@Override
	public Map<String, String> login(MemberDTO member) {

		Authentication authentication = null;
		
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getMemberPw()));
		}catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호 잘못 입력");
		}
	
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		
		log.info("로그인 성공....");
		log.info("인증에 성공한 사용자의 정보 : {}", user);
		
		
		Map<String, String> loginResponse = tokenService.generateToken(user.getUsername(),
																	user.getMemberNo());
		
		
		loginResponse.put("memberId", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());
		loginResponse.put("memberNo", String.valueOf(user.getMemberNo()));
		
		return loginResponse;
	}

	@Override
	public CustomUserDetails getUserDetails() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		
		return user;
	}
}

