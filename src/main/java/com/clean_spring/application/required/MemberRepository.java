package com.clean_spring.application.required;

import com.clean_spring.domain.Member;
import org.springframework.data.repository.Repository;

// 회원 저장, 조회 인터페이스
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);
    Member findByEmail(String email);
}
