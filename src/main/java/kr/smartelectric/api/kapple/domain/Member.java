package kr.smartelectric.api.kapple.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50000)
    private String firebaseUid;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String customerNumber;
    @Column(length = 50000)
    private String firebaseMessageToken;
    @Column
    private Boolean isSmartMeter;
    @Column
    private Boolean isMemberActive;
    @CreatedDate
    private LocalDateTime firstCreated;
    @LastModifiedDate
    private LocalDateTime lastModified;

    @Builder
    public Member(String firebaseUid, String name, String email, String customerNumber,
                  String firebaseMessageToken, Boolean isSmartMeter, Boolean isMemberActive){
        // null 값 검증
        Assert.hasText(firebaseUid, "firebase UID is empty");
        Assert.hasText(name, "name is empty");
        Assert.hasText(email, "email is empty");
        Assert.hasText(customerNumber, "customerNumber is empty");
        Assert.hasText(firebaseMessageToken, "firebaseMessageToken is empty");
        // 값 주입
        this.firebaseUid = firebaseUid;
        this.name = name;
        this.email = email;
        this.customerNumber = customerNumber;
        this.firebaseMessageToken = firebaseMessageToken;
        this.isSmartMeter = isSmartMeter;
        this.isMemberActive = isMemberActive;
    }

    public void deactivateMember(){
        this.isMemberActive = false;
    }
    public void activateMember(){ this.isMemberActive = true; }
}
