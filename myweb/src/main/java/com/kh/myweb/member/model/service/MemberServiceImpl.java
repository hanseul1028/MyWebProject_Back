package com.kh.myweb.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.myweb.member.model.dao.MemberMapper;
import com.kh.myweb.member.model.dto.MemberDTO;
import com.kh.myweb.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberDTO dto) {
        // MemberDTO → Member 로 변환
        Member memberValue = Member.builder()
            .memberNo(null) // DB에서 SEQ로 처리 
            .memberId(dto.getMemberId())
            .memberPw(passwordEncoder.encode(dto.getMemberPw())) // 암호화
            .memberName(dto.getMemberName())
            .build();
 
        memberMapper.signUp(memberValue); // DB 저장
    }
}
