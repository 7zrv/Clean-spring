package com.clean_spring.application.member.required;

import com.clean_spring.domain.shared.Email;
import com.clean_spring.domain.member.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

// 회원 저장, 조회 인터페이스
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);
}
