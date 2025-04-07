package com.kh.myweb.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.myweb.auth.model.vo.CustomUserDetails;
import com.kh.myweb.auth.model.vo.CustomUserDetails.CustomUserDetailsBuilder;
import com.kh.myweb.exception.CustomAuthenticationException;
import com.kh.myweb.member.model.dao.MemberMapper;
import com.kh.myweb.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	// AuthenticationManager가 실질적으로 사용자의 정보를 조회하는 클래스
	
	private final MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		MemberDTO user = mapper.getMemberByMemberId(username);
		
		if(user == null) {
			throw new CustomAuthenticationException("존재하지 않는 사용자입니다.");
		}
		
		// 사용자가 입력한 아이디 값이 테이블에 존재하긴 함
		
		 
		return  CustomUserDetails.builder().memberNo(user.getMemberNo())
										  .username(user.getMemberId())
										  .password(user.getMemberPw())
										  .memberName(user.getMemberName())
										  .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
										  .build();

	}

}