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
	
	private Long memberNo;


	@Size(min = 5, max = 15, message = "아이디는 5~15자여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "아이디는 영어와 숫자 조합만 가능합니다.")
	@NotBlank(message = "아이디를 입력해주세요.")
	private String memberId;
	

	@Size(min = 6, max = 20, message = "비밀번호는 6~20자여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "비밀번호는 영어와 숫자 조합만 가능합니다.")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String memberPw;
	private String memberName;
	private String email;
}
