package com.clean_spring.application.member;

import com.clean_spring.application.member.provided.MemberFinder;
import com.clean_spring.application.member.provided.MemberRegister;
import com.clean_spring.application.member.required.EmailSender;
import com.clean_spring.application.member.required.MemberRepository;
import com.clean_spring.domain.member.DuplicateEmailException;
import com.clean_spring.domain.member.Member;
import com.clean_spring.domain.member.MemberRegisterRequest;
import com.clean_spring.domain.member.PasswordEncoder;
import com.clean_spring.domain.shared.Email;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class MemberModifyService implements MemberRegister {

    private final MemberFinder memberFinder;
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(@Valid MemberRegisterRequest registerRequest) {
        // check

        checkDuplicateEmail(registerRequest);

        // domain model
        Member member = Member.register(registerRequest, passwordEncoder);

        // repository
        memberRepository.save(member);

        // post process
        sendWelcomeEmail(member);

        return member;
    }

    @Override
    public Member activate(Long memberId) {
        Member member = memberFinder.find(memberId);

        member.activate();

        return memberRepository.save(member);
    }

    private void sendWelcomeEmail(Member member) {
        emailSender.send(member.getEmail(), "Welcome!", "Thank you for registering.");
    }

    private void checkDuplicateEmail(MemberRegisterRequest registerRequest) {
        if (memberRepository.findByEmail(new Email(registerRequest.email())).isPresent()) {;
            throw new DuplicateEmailException("Email already exists: ");
        }
    }

}
