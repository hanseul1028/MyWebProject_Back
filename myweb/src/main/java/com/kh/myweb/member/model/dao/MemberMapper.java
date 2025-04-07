package com.kh.myweb.member.model.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.myweb.member.model.dto.MemberDTO;
import com.kh.myweb.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	// 회원가입 
	//@Insert("INSERT INTO MY_MEMBER VALUES(SEQ_BM.NEXTVAL, #{memberId}, #{memberPw}, #{memberName} , #{email} )")
	// int signUp(Member member);
	
//	@Insert("INSERT INTO MY_MEMBER (MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NAME, EMAIL) " +
//	        "VALUES (SEQ_BM.NEXTVAL, #{memberId}, #{memberPw}, #{memberName}, #{email})")
	
	@Insert("INSERT INTO MY_MEMBER (MEMBER_NO, MEMBER_ID, MEMBER_PW, MEMBER_NAME, EMAIL) " +
	        "VALUES (SEQ_MEMBER_NO.NEXTVAL, #{memberId}, #{memberPw}, #{memberName}, #{email})")
	int signUp(Member member);
	
	@Select("SELECT MEMBER_NO memberNo, MEMBER_ID memberid, MEMBER_PW memberPw, MEMBER_NAME memberName FROM MY_MEMBER WHERE MEMBER_ID = #{memberId}")
	MemberDTO getMemberByMemberId(String memberId);
	
	@Update("UPDATE MY_MEMBER SET MEMBER_PW = #{encodedPassword} WHERE MEMBER_NO = #{memberNo}")
	void changePassword(Map<String, Object> changeRequest);
	
	@Delete("DELETE FROM MY_MEMBER WHERE MEMBER_NO = #{memberNo}")
	void deleteByPassword(Long memberNo);
}
 