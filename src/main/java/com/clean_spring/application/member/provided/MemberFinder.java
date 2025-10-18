package com.clean_spring.application.member.provided;

import com.clean_spring.domain.member.Member;

public interface MemberFinder {
    Member find(Long memberId);
}
