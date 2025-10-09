package com.clean_spring.application.provided;

import com.clean_spring.SplearnTestConfiguration;
import com.clean_spring.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Import(SplearnTestConfiguration.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public record MemberRegisterTest(MemberRegister memberRegister) {

    @Test
    @DisplayName("")
    void register() {
        // given
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        // when & then
        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);

    }

    @Test
    @DisplayName("")
    void duplicateEmailFail() {
        // given
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        // when & then
        assertThatThrownBy(
                () -> memberRegister.register(MemberFixture.createMemberRegisterRequest())
        ).isInstanceOf(DuplicateEmailException.class);

    }
}
