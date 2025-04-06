package com.kh.myweb.member.model.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.myweb.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	// 회원가입 
	@Insert("INSERT INTO MY_MEMBER VALUES(SEQ_BM.NEXTVAL, #{memberId}, #{memberPw}, #{memberName} , #{email} )")
	int signUp(Member member);
}
 