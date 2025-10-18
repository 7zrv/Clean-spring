package com.clean_spring;

import com.clean_spring.application.member.required.EmailSender;
import com.clean_spring.domain.MemberFixture;
import com.clean_spring.domain.member.PasswordEncoder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SplearnTestConfiguration {
    @Bean
    public EmailSender emailSender() {
        return (email, subject, body) -> System.out.println("Sending email to: " + email);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return MemberFixture.createPasswordEncoder();
    }
}
