package kr.smartelectric.api.kapple.repository;

import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.MemberDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void initializeMemberRepository(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("멤버가 저장소에 저장되는지 테스트")
    void saveTest() {
        // DTO instance
        MemberDTO memberDTO = new MemberDTO(
                "firebaseUid",
                "name",
                "email",
                "customerNumber",
                "firebaseMessageToken",
                true
                );
        // Member instance
        Member member = Member.builder()
                .firebaseUid(memberDTO.getFirebaseUid())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .customerNumber(memberDTO.getCustomerNumber())
                .firebaseMessageToken(memberDTO.getFirebaseMessageToken())
                .isSmartMeter(memberDTO.getIsSmartMeter())
                .isMemberActive(Boolean.TRUE)
                .build();
        // Repository save (Member id generated)
        memberRepository.save(member);
        // Member Id
        Long memberId = member.getId();
        // find member and get Optional
        Optional<Member> memberFoundByIdOptional = memberRepository.findById(memberId);
        // Boolean for isPresent
        boolean memberOptionalPresent = memberFoundByIdOptional.isPresent();
        // null 아닌지 검증
        assertTrue(memberOptionalPresent);
        // 값 있으면 same type instance 인지 검증
        assertSame(member, memberFoundByIdOptional.get());
    }

    @Test
    @DisplayName("이메일 활성화 테스트")
    void isMemberActiveTest() {
        // DTO instance
        MemberDTO memberDTO = new MemberDTO(
                "firebaseUid",
                "name",
                "email",
                "customerNumber",
                "firebaseMessageToken",
                true
        );
        // Member instance
        Member member = Member.builder()
                .firebaseUid(memberDTO.getFirebaseUid())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .customerNumber(memberDTO.getCustomerNumber())
                .firebaseMessageToken(memberDTO.getFirebaseMessageToken())
                .isSmartMeter(memberDTO.getIsSmartMeter())
                .isMemberActive(Boolean.TRUE)
                .build();
        // Repository save (Member id generated)
        memberRepository.save(member);
        Boolean existenceByEmail = memberRepository.existsByEmailEqualsAndIsMemberActiveTrue("email");
        Assertions.assertTrue(existenceByEmail);
        Optional<Member> memberFoundByEmail = memberRepository.getMemberByEmailEqualsAndIsMemberActiveTrue("email");
        Assertions.assertTrue(memberFoundByEmail.isPresent());
        Assertions.assertSame(memberFoundByEmail.get(), member);
    }
}
