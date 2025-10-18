package com.clean_spring.application.member.provided;

import com.clean_spring.domain.member.Member;
import com.clean_spring.domain.member.MemberRegisterRequest;
import jakarta.validation.Valid;

// 회원 등록 기능 제공 인터페이스
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);
}
