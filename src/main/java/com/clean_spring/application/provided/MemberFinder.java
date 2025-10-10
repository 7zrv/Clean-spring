package com.clean_spring.application.provided;

import com.clean_spring.domain.Member;

public interface MemberFinder {
    Member find(Long memberId);
}
