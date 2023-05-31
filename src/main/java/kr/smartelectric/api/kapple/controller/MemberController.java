package kr.smartelectric.api.kapple.controller;

import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.MemberDTO;
import kr.smartelectric.api.kapple.firebase.FirebaseCloudMessagingManager;
import kr.smartelectric.api.kapple.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FirebaseCloudMessagingManager firebaseCloudMessagingManager;

    @ExceptionHandler
    public void errorHandle(Exception e, HttpServletResponse res){
        e.printStackTrace();
        res.setStatus(403);
        res.setHeader("x-amzn-errortype", "AccessDeniedException");
        // Content-Type: text/html;charset=utf-8
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        try{
            PrintWriter writer = res.getWriter();
            writer.println(e.getMessage());
        }catch (Exception writerException){
            writerException.printStackTrace();
        }

    }

    @PostMapping(value = "/user/info/modification")
    public ResponseEntity<Map<String, String>> modifyUserInfo(@RequestParam String email,
                                                 @RequestParam String parameterName,
                                                 @RequestParam String parameterValue){
        Map<String, String> resultMap = new HashMap<>();
        String resultStatus = memberService.setMemberParameters(email, parameterName, parameterValue);
        resultMap.put("result", resultStatus);
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping(value = "/user/signup")
    public ResponseEntity<String> saveUser(@RequestBody MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        if(Boolean.TRUE.equals(memberService.checkMemberActive(memberDTO.getEmail()))){
            return ResponseEntity.ok("user already in database");
        }else{
            Member member = memberService.saveMember(memberDTO);
            return ResponseEntity.ok(member.toString());
        }
    }

    @GetMapping(value = "/user/search", produces="application/json")
    public ResponseEntity<Member> getUser(String email) {
        Optional<Member> memberOptional = memberService.findMember(email);
        return ResponseEntity.ok(memberOptional.get());
    }

    @GetMapping("/email/validation")
    public ResponseEntity<Map<String, Boolean>> emailValidate(@RequestParam String email){
        Boolean emailActive = memberService.checkMemberActive(email);
        Map<String, Boolean> responseJson = new HashMap<>();
        responseJson.put("result", emailActive);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseJson);
    }

    @PostMapping("/user/deactivate")
    public ResponseEntity<Map<String, String>> emailDeactivate(@RequestParam String email){
        String result = memberService.deleteMember(email);
        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("result", result);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(responseJson);
    }

    @PostMapping("/user/activate")
    public ResponseEntity<Map<String, String>> emailActivate(@RequestParam String email){
        String result = memberService.activateIsMemberActive(email);
        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("result", result);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(responseJson);
    }

    @GetMapping("/message")
    public ResponseEntity<String> sendMessage(){
        firebaseCloudMessagingManager.sendMessage();
        return ResponseEntity
                .ok("complete");
    }
}
