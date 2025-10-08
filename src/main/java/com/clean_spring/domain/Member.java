package com.clean_spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(createRequest.email());
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
