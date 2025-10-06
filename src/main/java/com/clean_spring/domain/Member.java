package com.clean_spring.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Objects;

@Getter
@ToString
public class Member {
    private String email;

    private String nickname;

    private String password;

    private MemberStatus status;

    private Member(String email, String nickname, String password) {
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.password = Objects.requireNonNull(password);

        this.status = MemberStatus.PENDING;
    }

    public static Member create(String email, String nickname, String password, PasswordEncoder passwordEncoder) {
        return new Member(email, nickname, passwordEncoder.encode(password));
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
        this.nickname = Objects.requireNonNull(nickname);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

}
