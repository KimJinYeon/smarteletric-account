package kr.smartelectric.api.kapple.service;

import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.MemberDTO;
import kr.smartelectric.api.kapple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(MemberDTO memberDTO) {
        Member member = Member.builder()
                .firebaseUid(memberDTO.getFirebaseUid())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .customerNumber(memberDTO.getCustomerNumber())
                .firebaseMessageToken(memberDTO.getFirebaseMessageToken())
                .isSmartMeter(memberDTO.getIsSmartMeter())
                .isMemberActive(Boolean.TRUE)
                .build();
        memberRepository.save(member);
        return member;
    }

    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    public Optional<Member> findMember (String email){
        return memberRepository.getMemberByEmailEqualsAndIsMemberActiveTrue(email);
    }

    public Boolean checkMemberActive(String email) {
        return memberRepository.existsByEmailEqualsAndIsMemberActiveTrue(email);
    }

    public String deactivateIsMemberActive(String email) {

        Optional<Member> memberOptional = memberRepository.getMemberByEmailEqualsAndIsMemberActiveTrue(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.deactivateMember();
            memberRepository.save(member);
            return "deactivated";
        }else{
            return "not found";
        }
    }

    public String activateIsMemberActive(String email){
        Boolean memberActivated = memberRepository.existsByEmailEqualsAndIsMemberActiveFalse(email);
        if(Boolean.TRUE.equals(memberActivated)) {
            Member member = memberRepository.getMemberByEmailEqualsAndIsMemberActiveFalse(email);
            member.activateMember();
            memberRepository.save(member);
            return "activated";
        }else{
            return "not found";
        }
    }

    public List<Member> getMembersByCustomerNumber(List<String> customerNumbers){
        return memberRepository.getMembersByCustomerNumberIn(customerNumbers);
    }

    private Optional<Member> getMemberOptionalByEmail(String email) {
        return memberRepository.getMemberByEmailEqualsAndIsMemberActiveTrue(email);
    }

    public String setMemberParameters(String email, String parameterName, String parameterValue) {

        Optional<Member> memberOptionalByEmail = getMemberOptionalByEmail(email);
        Member member;

        if(memberOptionalByEmail.isPresent()){
            member = memberOptionalByEmail.get();
        }else{
            return "requested member not found";
        }

        switch (parameterName) {
            case "name":
                member.setName(parameterValue);
                break;
            case "customerNumber":
                member.setCustomerNumber(parameterValue);
                break;
            case "firebaseMessageToken":
                member.setFirebaseMessageToken(parameterValue);
                break;
            case "isSmartMeter":
                member.setIsSmartMeter(Boolean.valueOf(parameterValue));
                break;
        }
        memberRepository.save(member);
        return "requested change completed";
    }

    public String deleteMember(String email){
        Optional<Member> memberOptional = memberRepository.getMemberByEmailEquals(email);
        if (memberOptional.isPresent()){
            Member member = memberOptional.get();
            memberRepository.delete(member);
            return "deleted";
        }else{
            return "not found";
        }
    }
}
