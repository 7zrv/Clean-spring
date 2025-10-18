package com.clean_spring.application.member.required;

import com.clean_spring.domain.member.Member;
import com.clean_spring.domain.MemberFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("")
    void createMember() {
        // given
        Member member = Member.register(MemberFixture.createMemberRegisterRequest(), MemberFixture.createPasswordEncoder());

        assertThat(member.getId()).isNull();

        // when
        memberRepository.save(member);

        // then
        entityManager.flush();
        assertThat(member.getId()).isNotNull();
    }

    @Test
    @DisplayName("")
    void duplicateEmailFail() {
        // given
        Member member1 = Member.register(MemberFixture.createMemberRegisterRequest(), MemberFixture.createPasswordEncoder());
        memberRepository.save(member1);

        Member member2 = Member.register(MemberFixture.createMemberRegisterRequest(), MemberFixture.createPasswordEncoder());

        // when & then
        assertThatThrownBy(
                () -> memberRepository.save(member2)
        ).isInstanceOf(DataIntegrityViolationException.class);
    }
    
}