package com.kh.myweb.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.myweb.member.model.dao.MemberMapper;
import com.kh.myweb.member.model.dto.MemberDTO;
import com.kh.myweb.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void signUp(MemberDTO dto) {
        // MemberDTO → Member 로 변환
        Member memberValue = Member.builder()
            .memberNo(null) // DB에서 SEQ로 처리
            .memberId(dto.getMemberId())
            .memberPw(dto.getMemberPw()) // 암호화가 필요하다면 여기서!
            .memberName(dto.getMemberName())
            // .role(dto.getRole() != null ? dto.getRole() : "USER") // 기본값 설정
            .build();
 
        memberMapper.signUp(memberValue); // DB 저장
    }
}
