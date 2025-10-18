package com.clean_spring.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.clean_spring.domain.MemberFixture.createMemberRegisterRequest;
import static com.clean_spring.domain.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void  setUp() {
        this.passwordEncoder = createPasswordEncoder();

        member = Member.register(createMemberRegisterRequest(), passwordEncoder);
    }

    @Test
    @DisplayName("")
    void registerMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
        assertThat(member.getDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    @DisplayName("")
    void activate() {
        //when
        member.activate();

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVATE);
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
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
        assertThat(member.getDetail().getActivatedAt()).isNotNull();
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
        assertThat(member.verifyPassword("secretsecret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("12341234", passwordEncoder)).isFalse();
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

    @Test
    @DisplayName("")
    void isActive() {
        assertThat(member.isActive()).isFalse();

        member.activate();
        assertThat(member.isActive()).isTrue();

        member.deActivate();
        assertThat(member.isActive()).isFalse();
    }

    @Test
    @DisplayName("")
    void invalidEmail() {
        // given

        // when

        // then
        assertThatThrownBy(() -> 
            Member.register(createMemberRegisterRequest("invalid email"), passwordEncoder)
        ).isInstanceOf(IllegalArgumentException.class);

        Member.register(new MemberRegisterRequest("seojin@gmail.app", "seojin", "secret"), passwordEncoder);
    }

    @Test
    @DisplayName("")
    void updateInfo() {
        // given
        member.activate();

        // when
        var request = new MemberInfoUpdateRequest("newNickName", "1234", "hi");
        member.updateInfo(request);

        // then
        assertThat(member.getNickname()).isEqualTo("newNickName");
        assertThat(member.getDetail().getProfile().address()).isEqualTo("1234");
        assertThat(member.getDetail().getIntroduce()).isEqualTo("hi");
    }

}
