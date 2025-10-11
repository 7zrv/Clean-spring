package com.clean_spring.adapter.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SecurePasswordEncoderTest {

    @Test
    @DisplayName("")
    void securePasswordEncoder() {
        // given
        SecurePasswordEncoder securePasswordEncoder = new SecurePasswordEncoder();

        // when
        String encodedPassword = securePasswordEncoder.encode("rawPassword");

        // then
        assertThat(securePasswordEncoder.matches("rawPassword", encodedPassword)).isTrue();
        assertThat(securePasswordEncoder.matches("wrongPassword", encodedPassword)).isFalse();
    }
}