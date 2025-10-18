package com.clean_spring.domain.member;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
