package com.clean_spring.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    @DisplayName("프로필 VO 생성자 테스트")
    void profile() {
        new Profile("1234");
        new Profile("abcd");
        new Profile("seojon");
    }

    @Test
    @DisplayName("")
    void profileFail() {
        assertThatThrownBy(
                () -> new Profile("")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> new Profile(" ")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> new Profile("!!!!!!!")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}