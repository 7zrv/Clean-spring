package com.clean_spring.domain;

import com.clean_spring.domain.shared.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {

    @Test
    @DisplayName("")
    void equality() {
        // given
        var email1 = new Email("seojin@gmail.app");
        var email2 = new Email("seojin@gmail.app");

        // when

        // then
        assertThat(email1).isEqualTo(email2);
    }

}