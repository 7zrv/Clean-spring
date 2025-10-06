package com.clean_spring.domain;

public record MemberCreateRequest(String email, String nickname, String password) {
}
