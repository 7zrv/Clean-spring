package com.clean_spring.adapter.intergration;

import com.clean_spring.application.member.required.EmailSender;
import com.clean_spring.domain.shared.Email;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
@Fallback
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("Dummy email sent to: " + email);
    }
}
