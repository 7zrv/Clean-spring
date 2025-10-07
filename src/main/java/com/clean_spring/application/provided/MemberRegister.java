package com.clean_spring.application.provided;

import com.clean_spring.domain.Member;
import com.clean_spring.domain.MemberRegisterRequest;

// 회원 등록 기능 제공 인터페이스
public interface MemberRegister {
    Member register(MemberRegisterRequest registerRequest);
}
