package kr.smartelectric.api.kapple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.interceptor.CustomJwtInterceptor;
import kr.smartelectric.api.kapple.repository.MemberRepository;
import kr.smartelectric.api.kapple.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @MockBean
    CustomJwtInterceptor customJwtInterceptor;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @BeforeEach
    void initializeMemberRepository() throws Exception {
        when(customJwtInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        memberRepository.deleteAll();
    }

    @Test
    void basicTest() throws Exception{
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firebaseUid", "firebaseUidTest");
        requestBody.put("name", "nameTest");
        requestBody.put("email", "emailTest");
        requestBody.put("customerNumber", "customerNumberTest");
        requestBody.put("firebaseMessageToken", "f55a9OzBtEygq12yJHGm6B:APA91bGm0cUlTPXBY0jfHUoRNP-UndP6ZC11fE9k9nyWiNuzo_OfFdfg8uS4kPHbJrlxGuoWHzUD1OBuZeQd3a4RaeTIOcB-ofTzlMaO6dr2tAdcSdEYghPfMD2TNyIZlUdHhaNp8yiX");
        requestBody.put("isSmartMeter", Boolean.TRUE);
        System.out.println("requestBody.toString() = " + requestBody.toString());
        mockMvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andDo(print());
        Optional<Member> memberFoundByEmailOptional = memberRepository.findByEmailEquals("emailTest");
        assertTrue(memberFoundByEmailOptional.isPresent());
        Member memberFoundByEmail = memberFoundByEmailOptional.get();
        assertEquals(memberFoundByEmail.getFirebaseUid(), "firebaseUidTest");
        assertEquals(memberFoundByEmail.getName(), "nameTest");
        assertEquals(memberFoundByEmail.getEmail(), "emailTest");
        assertEquals(memberFoundByEmail.getCustomerNumber(), "customerNumberTest");
        assertEquals(memberFoundByEmail.getFirebaseMessageToken(), "f55a9OzBtEygq12yJHGm6B:APA91bGm0cUlTPXBY0jfHUoRNP-UndP6ZC11fE9k9nyWiNuzo_OfFdfg8uS4kPHbJrlxGuoWHzUD1OBuZeQd3a4RaeTIOcB-ofTzlMaO6dr2tAdcSdEYghPfMD2TNyIZlUdHhaNp8yiX");
        assertTrue(memberFoundByEmail.getIsSmartMeter());
        assertTrue(memberFoundByEmail.getIsMemberActive());
        Assertions.assertNotNull(memberFoundByEmail.getLastModified());
    }
}
