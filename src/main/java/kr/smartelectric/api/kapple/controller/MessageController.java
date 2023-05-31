package kr.smartelectric.api.kapple.controller;

import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.TargetMemberDTO;
import kr.smartelectric.api.kapple.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class MessageController {
    private final MemberService memberService;

//    @PostMapping("/message/specific-user")
//    public void publishMessageToSpecificUsers(@RequestBody TargetMemberDTO targetMemberDTO){
//        List<String> customerNumberList = targetMemberDTO.getCustomerNumberList();
//    }
}
