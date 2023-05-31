package kr.smartelectric.api.kapple.domain.dto;

import kr.smartelectric.api.kapple.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TargetMemberDTO {
    private List<String> customerNumberList;
}
