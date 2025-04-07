package com.kh.myweb.auth.service;

import java.util.Map;

import com.kh.myweb.auth.model.vo.CustomUserDetails;
import com.kh.myweb.member.model.dto.MemberDTO;

public interface AuthService {
	
	Map<String, String> login(MemberDTO member);
	
	CustomUserDetails getUserDetails();
	

}
