package hackertontest.test.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class Member {

    private long memberId;
    private String name;

    // 추후 데이터 추가 예정
}
