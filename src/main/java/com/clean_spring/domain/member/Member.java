package com.clean_spring.domain.member;

import com.clean_spring.domain.AbstractEntity;
import com.clean_spring.domain.shared.Email;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.Assert;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@ToString(callSuper = true, exclude = "detail")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Member extends AbstractEntity {

    @NaturalId
    private Email email;

    private String nickname;

    private String password;

    private MemberStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private MemberDetail detail;

    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(createRequest.email());
        member.nickname = requireNonNull(createRequest.nickname());
        member.password = requireNonNull(passwordEncoder.encode(createRequest.password()));

        member.status = MemberStatus.PENDING;

        member.detail = MemberDetail.create();

        return member;
    }

    public void activate() {
        Assert.state(status.equals(MemberStatus.PENDING), "가입대기 상태가 아닙니다.");

        this.status = MemberStatus.ACTIVATE;
        this.detail.activate();
    }

    public void  deActivate() {
        Assert.state(status.equals(MemberStatus.ACTIVATE), "활성화 상태가 아닙니다.");

        this.status = MemberStatus.DEACTIVATED;
        this.detail.deactivate();
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public void updateInfo(String nickname) {
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status.equals(MemberStatus.ACTIVATE);
    }

}
