package com.clean_spring.application.provided;

import com.clean_spring.SplearnTestConfiguration;
import com.clean_spring.domain.Member;
import com.clean_spring.domain.MemberFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
record MemberFinderTest(MemberFinder memberFinder, MemberRegister memberRegister, EntityManager entityManager) {

    @Test
    @DisplayName("")
    void find() {
        // given
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        // when
        Member foundMember = memberFinder.find(member.getId());

        // then
        assertThat(foundMember.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("")
    void findFail() {
        assertThatThrownBy(
                () -> memberFinder.find(999L)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}