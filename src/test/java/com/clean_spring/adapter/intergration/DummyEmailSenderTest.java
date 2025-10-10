package com.clean_spring.adapter.intergration;

import com.clean_spring.domain.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DummyEmailSenderTest {

    @Test
    @StdIo
    void dummyEmailSender(StdOut stdOut) {
        // given
        DummyEmailSender dummyEmailSender = new DummyEmailSender();

        // when
        dummyEmailSender.send(new Email("seojin@gmail.app"), "subject", "body");

        // then
        assertThat(stdOut.capturedLines()[0]).isEqualTo("Dummy email sent to: Email[address=seojin@gmail.app]");
    }

}