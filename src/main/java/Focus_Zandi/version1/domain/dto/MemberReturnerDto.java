package Focus_Zandi.version1.domain.dto;

import Focus_Zandi.version1.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberReturnerDto {

    private String username;
    private String email;

    private String name;
    private String gender;
    private String dob;

    private String occupation;
    private String place;

    public MemberReturnerDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.name = member.getName();
        this.gender = member.getGender();
        this.dob = member.getDob();
        this.occupation = member.getOccupation();
        this.place = member.getPlace();
    }
}
