package com.clean_spring.application.member;

import com.clean_spring.application.member.provided.MemberFinder;
import com.clean_spring.application.member.required.MemberRepository;
import com.clean_spring.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberQueryService implements MemberFinder {

    private  final MemberRepository memberRepository;

    @Override
    public Member find(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
