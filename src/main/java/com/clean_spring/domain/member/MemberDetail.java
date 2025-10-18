package com.clean_spring.domain.member;

import com.clean_spring.domain.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {

    private Profile profile;

    private String introduce;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    void activate(){
        Assert.isTrue(activatedAt == null, "이미 활성화된 회원입니다.");

        this.activatedAt = LocalDateTime.now();
    }

    void deactivate(){
        Assert.isTrue(deactivatedAt == null, "이미 비활성화된 회원입니다.");

        this.deactivatedAt = LocalDateTime.now();
    }
}
