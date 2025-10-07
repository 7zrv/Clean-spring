package com.clean_spring.domain;

public record MemberRegisterRequest(String email, String nickname, String password) {
}
