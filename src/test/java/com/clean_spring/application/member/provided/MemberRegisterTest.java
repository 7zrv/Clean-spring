package com.clean_spring.application.member.provided;

import com.clean_spring.SplearnTestConfiguration;
import com.clean_spring.domain.*;
import com.clean_spring.domain.member.DuplicateEmailException;
import com.clean_spring.domain.member.Member;
import com.clean_spring.domain.member.MemberRegisterRequest;
import com.clean_spring.domain.member.MemberStatus;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
record MemberRegisterTest(MemberRegister memberRegister, EntityManager entityManager) {

    @Test
    void register() {
        // given
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        // when & then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        // given
        memberRegister.register(MemberFixture.createMemberRegisterRequest());

        // when & then
        assertThatThrownBy(
                () -> memberRegister.register(MemberFixture.createMemberRegisterRequest())
        ).isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void activate() {
        // given
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        // when
        member = memberRegister.activate(member.getId());
        entityManager.flush();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVATE);
    }

    @Test
    void memberRegisterRequestFail() {
        checkValidation(new MemberRegisterRequest(
                "seojin@gmail.",
                "seojin",
                "1234"
        ));
        checkValidation(new MemberRegisterRequest(
                "seojin@gmail.app"
                , "s"
                , "12341234"
        ));
        checkValidation(new MemberRegisterRequest(
                "seojin@gmail.app"
                , "seojin"
                , "1234"
        ));
    }

    private void checkValidation(MemberRegisterRequest invalidRequest) {
        assertThatThrownBy(() -> memberRegister.register(invalidRequest))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
