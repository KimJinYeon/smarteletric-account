package kr.smartelectric.api.kapple.service;

import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.MemberDTO;
import kr.smartelectric.api.kapple.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("멤버 저장 메소드")
    void saveMember(){
        // DTO instance
        MemberDTO memberDTO = new MemberDTO(
                "firebaseUid",
                "name",
                "email",
                "customerNumber",
                "firebaseMessageToken",
                true
        );
        // 멤버 저장
        Member member = memberService.saveMember(memberDTO);
        // id 찾기
        Long memberId = member.getId();
        // 멤버 찾기
        Optional<Member> memberFoundByIdOptional = memberService.findMember(memberId);
        // 멤버 찾는지 검증
        Assertions.assertTrue(memberFoundByIdOptional.isPresent());
        // 같은 instance type 인지 검증
        Assertions.assertSame(member, memberFoundByIdOptional.get());
    }


}
