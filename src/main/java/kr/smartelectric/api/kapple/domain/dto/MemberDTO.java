package kr.smartelectric.api.kapple.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String firebaseUid;
    private String name;
    private String email;
    private String customerNumber;
    private String firebaseMessageToken;
    private Boolean isSmartMeter;
}
