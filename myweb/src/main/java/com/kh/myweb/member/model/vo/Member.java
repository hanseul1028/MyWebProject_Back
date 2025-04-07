package com.kh.myweb.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Member {

	private Long memberNo;
	private String memberPw;
	private String memberId;
	private String memberName;
	private String email;
}
