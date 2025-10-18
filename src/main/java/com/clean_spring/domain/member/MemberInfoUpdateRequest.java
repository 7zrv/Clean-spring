package com.clean_spring.domain.member;

public record MemberInfoUpdateRequest(
        String nickName,
        String profileAddress,
        String introduction
) {
}
