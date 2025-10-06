package com.clean_spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void  setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };

        member = Member.create("seojin@gmail.app", "seojin", "secret", passwordEncoder);
    }

    @Test
    @DisplayName("")
    void createMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    @DisplayName("")
    void activate() {
        //when
        member.activate();

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVATE);
    }

    @Test
    @DisplayName("")
    void activateFail() {
        //when
        member.activate();

        //then
        assertThatThrownBy(() -> {
           member.activate();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("")
    void deActivate() {

        //given
        member.activate();

        //when
        member.deActivate();

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    @DisplayName("")
    void deActivateFail() {
        // given
        member.activate();
        member.deActivate();

        // when

        // then
        assertThatThrownBy(() -> {
            member.deActivate();
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("")
    void verifyPassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("1234", passwordEncoder)).isFalse();
    }

    @Test
    @DisplayName("")
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("seojin");

        member.changeNickname("newSeojin");

        assertThat(member.getNickname()).isEqualTo("newSeojin");
    }

    @Test
    @DisplayName("")
    void changePassword() {
        // given

        // when
        member.changePassword("newSecret", passwordEncoder);

        // then
        assertThat(member.verifyPassword("newSecret", passwordEncoder)).isTrue();
    }

}
