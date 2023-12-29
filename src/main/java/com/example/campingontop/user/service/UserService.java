package com.example.campingontop.service;

import com.example.campingontop.model.dto.PostMemberDto;
import com.example.campingontop.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(PostMemberDto postMemberDto) {

    }
}
