//package com.clean_spring.application.provided;
//
//import com.clean_spring.application.MemberService;
//import com.clean_spring.application.required.EmailSender;
//import com.clean_spring.application.required.MemberRepository;
//import com.clean_spring.domain.Email;
//import com.clean_spring.domain.Member;
//import com.clean_spring.domain.MemberFixture;
//import com.clean_spring.domain.MemberStatus;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//class MemberRegisterManualTest {
//
//    @Test
//    @DisplayName("")
//    void registerTestStub() {
//        // given
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                new EmailSenderStub(),
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//    }
//
//    @Test
//    @DisplayName("")
//    void registerTestMock() {
//        // given
//        EmailSenderMock emailSenderMock = new EmailSenderMock();
//
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                emailSenderMock,
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        assertThat(emailSenderMock.getTos()).hasSize(1);
//        assertThat(emailSenderMock.getTos().getFirst()).isEqualTo(member.getEmail());
//    }
//
//    @Test
//    @DisplayName("")
//    void registerTestMockito() {
//        // given
//        EmailSenderMock emailSenderMock = Mockito.mock(EmailSenderMock.class);
//
//        MemberRegister register = new MemberService(
//                new MemberRepositoryStub(),
//                emailSenderMock,
//                MemberFixture.createPasswordEncoder()
//        );
//
//        // when
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        // then
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        Mockito.verify(emailSenderMock, Mockito.atLeastOnce()).send(eq(member.getEmail()), any(), any());
//    }
//
//    static class MemberRepositoryStub implements MemberRepository {
//
//        @Override
//        public Member save(Member member) {
//            ReflectionTestUtils.setField(member, "id", 1L);
//            return member;
//        }
//
//        @Override
//        public Optional<Member> findByEmail(Email email) {
//            return Optional.empty();
//        }
//    }
//
//    static class EmailSenderStub implements EmailSender {
//
//        @Override
//        public void send(Email email, String subject, String body) {
//
//        }
//    }
//
//    public static class EmailSenderMock implements EmailSender {
//
//        List<Email> tos = new java.util.ArrayList<>();
//
//        @Override
//        public void send(Email email, String subject, String body) {
//            tos.add(email);
//        }
//
//        public List<Email> getTos() {
//            return tos;
//        }
//
//    }
//
//}