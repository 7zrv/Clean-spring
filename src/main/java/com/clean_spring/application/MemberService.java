package com.clean_spring.application;

import com.clean_spring.application.provided.MemberRegister;
import com.clean_spring.application.required.EmailSender;
import com.clean_spring.application.required.MemberRepository;
import com.clean_spring.domain.Member;
import com.clean_spring.domain.MemberRegisterRequest;
import com.clean_spring.domain.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberRegister {

    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        // check

        // domain model
        Member member = Member.register(registerRequest, passwordEncoder);

        // repository
        memberRepository.save(member);

        // post process
        emailSender.send(member.getEmail(), "Welcome!", "Thank you for registering.");

        return member;
    }
}
