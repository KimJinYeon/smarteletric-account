package kr.smartelectric.api.kapple.scheduler;

import com.google.gson.Gson;
import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.domain.dto.TargetMemberDTO;
import kr.smartelectric.api.kapple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class PowerUsageCheckScheduler {
    private final MemberRepository memberRepository;
    private final String url = "";
//    @Scheduled(cron = "0 0 2 * * *")
//    public void powerUsageNotificationWebHook(){
//        checkPowerUsageAccumulate();
//    }
//
//    private String checkPowerUsageAccumulate(){
//        List<Member> membersActive = memberRepository.getMembersByIsMemberActiveTrue();
//        TargetMemberDTO targetMemberDTO = new TargetMemberDTO(membersActive);
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        Gson gson = new Gson();
//        try{
//            HttpPost request = new HttpPost(url);
//            StringEntity stringEntity = new StringEntity(gson.toJson(targetMemberDTO));
//            request.setEntity(stringEntity);
//            request.setHeader("Content-type", "application/json");
//            HttpResponse response = httpClient.execute(request);
//            return response.toString();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "failed";
//    }
}
