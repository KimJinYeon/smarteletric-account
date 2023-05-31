package kr.smartelectric.api.kapple.firebase;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import kr.smartelectric.api.kapple.domain.Member;
import kr.smartelectric.api.kapple.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FirebaseCloudMessagingManager {

    private final MemberService memberService;
    // This registration token comes from the client FCM SDKs.
    String registrationToken = "";

    public void sendMessage(){

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();
        try{
            // Send a message to the device corresponding to the provided
            // registration token.
            String response = FirebaseMessaging.getInstance().send(message);
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void sendMultiMessage(List<Member> targetMembers){
        List<String> registrationTokens = new ArrayList<>();
        for (Member member : targetMembers) {
            registrationTokens.add(member.getFirebaseMessageToken());
        }
        MulticastMessage message = MulticastMessage.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .addAllTokens(registrationTokens)
                .build();
        try{
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            // See the BatchResponse reference documentation
            // for the contents of response.
            System.out.println(response.getSuccessCount() + " messages were sent successfully");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
