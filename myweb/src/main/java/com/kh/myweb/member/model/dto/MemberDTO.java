package com.kh.myweb.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

	@Size(min=5, max=15 , message="아이디는 5 글자 이상 15글자 이하")
	@Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디는 영어와 숫자 조합 4~20자여야 합니다.")
//	@Pattern(regexp ="^[a-zA-Z0-9]$", message="아이디 값은 영어/숫자만 가능")
	@NotBlank(message = "아이디를 입력해라")
	private String memberId;
	
	@Size(min=4, max=15 , message="비밀번호는 4 글자 이상 15글자 이하")
//	@Pattern(regexp ="^[a-zA-Z0-9]$", message="비밀번호 값은 영어/숫자만 가능")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "비밀번호는 영어와 숫자 조합 6~20자여야 합니다.")
	@NotBlank(message = "비밀번호를 입력해라")
	private String memberPw;
	private String memberName;
	private String email;
}
