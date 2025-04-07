package com.kh.myweb.auth.token.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.myweb.auth.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {

	@Insert("INSERT INTO MY_TOKEN (TOKEN, MEMBER_NO, EXPIRATION) "
		      + "VALUES (#{token}, #{memberNo}, #{expiration})")
	void saveToken(RefreshToken token);
	
	RefreshToken findByToken(RefreshToken token);
	
	void deleteExpiredRefreshToken();

	void deleteExpiredRefreshToken(Long now);
}
