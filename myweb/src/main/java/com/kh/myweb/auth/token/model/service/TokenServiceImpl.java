package com.kh.myweb.auth.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.myweb.auth.token.model.dao.TokenMapper;
import com.kh.myweb.auth.token.model.vo.RefreshToken;
import com.kh.myweb.auth.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	
	@Override
	public Map<String, String> generateToken(String username, Long memberNo) {
		
		Map<String, String> tokens = createToken(username);
		
		saveToken(tokens.get("refreshToken"), memberNo);
		
		return tokens;
	}
	
	private void saveToken(String refreshToken, Long memberNo) {
		RefreshToken token = RefreshToken.builder()
										.token(refreshToken)
										.memberNo(memberNo)
										.expiration(System.currentTimeMillis() + 36000000L * 72)
										.build();
		
		tokenMapper.saveToken(token);
	}
	
	private Map<String, String> createToken(String username){
		
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}

	@Override
	public Map<String, String> refreshToken(String refreshToken){
		RefreshToken token = RefreshToken.builder()
										.token(refreshToken)
										.build();
		
		RefreshToken responseToken =  tokenMapper.findByToken(token);
		
		if(responseToken == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("유효하지 않은 토큰 입니다 ");
		}
		
		String username = getUsernameByToken(refreshToken);
		Long memberNo = responseToken.getMemberNo();
		
		return generateToken(username, memberNo);
	}

	private String getUsernameByToken(String refreshToken) {
		
		Claims claims = tokenUtil.parseJwt(refreshToken);
		return claims.getSubject();
	}














}
