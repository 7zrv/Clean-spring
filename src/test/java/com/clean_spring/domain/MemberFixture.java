package com.clean_spring.domain;

import com.clean_spring.domain.member.MemberRegisterRequest;
import com.clean_spring.domain.member.PasswordEncoder;
import org.jetbrains.annotations.NotNull;

public class MemberFixture {

    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "seojin", "secretsecret");
    }

    public static @NotNull MemberRegisterRequest createMemberRegisterRequest() {
        return  createMemberRegisterRequest("seojin@gmail.app");
    }

    public static @NotNull PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };
    }

}
