package com.clean_spring.application.required;

import com.clean_spring.domain.Email;

// 이메일 전송 기능 인터페이스
public interface EmailSender {
    void send(Email email, String subject, String body);
}
