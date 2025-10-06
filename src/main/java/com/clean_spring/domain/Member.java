package com.clean_spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Getter
@ToString
public class Member {
    private String email;

    private String nickname;

    private String password;

    private MemberStatus status;

    private Member() {}

    public static Member create(MemberCreateRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = requireNonNull(createRequest.email());
        member.nickname = requireNonNull(createRequest.nickname());
        member.password = requireNonNull(passwordEncoder.encode(createRequest.password()));

        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate() {
        Assert.state(status.equals(MemberStatus.PENDING), "가입대기 상태가 아닙니다.");

        this.status = MemberStatus.ACTIVATE;
    }

    public void  deActivate() {
        Assert.state(status.equals(MemberStatus.ACTIVATE), "활성화 상태가 아닙니다.");

        this.status = MemberStatus.DEACTIVATED;
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status.equals(MemberStatus.ACTIVATE);
    }

}
