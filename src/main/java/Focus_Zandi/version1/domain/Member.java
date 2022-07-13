package Focus_Zandi.version1.domain;

import Focus_Zandi.version1.domain.dto.MemberRegisterDto;
import Focus_Zandi.version1.domain.dto.MemberUpdateDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = true, length = 100) //123456 => 해쉬 (비밀번호 암호화)
    private String password;
    @Column(nullable = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false)
    private String role;

    private String gender;
    private String dob;
    private int age;

    private String occupation;
    private String place;

    @CreationTimestamp
    private Timestamp createDate;

    // 비즈니스 로직//

    public static Member createMember(MemberRegisterDto registerDto) {
        System.out.println("Member.createMember");
        String dob = registerDto.getDob();
        int age = calcAge(dob);
        Member member = new Member();


        member.setUsername(registerDto.getUsername());
        member.setPassword(registerDto.getPassword());
        member.setEmail(registerDto.getEmail());

        member.setName(registerDto.getName());
        member.setDob(registerDto.getDob());
        member.setGender(registerDto.getGender());
        member.setAge(age);

        member.setOccupation(registerDto.getOccupation());
        member.setPlace(registerDto.getPlace());
        member.setRole("ROLE_USER");

        return member;
    }

    private static int calcAge(String dob) {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        int user_year = Integer.parseInt(dob.substring(0, 4));
        int user_month = Integer.parseInt(dob.substring(5, 7));
        int user_day = Integer.parseInt(dob.substring(8, 10));

        int age = 0;

        if (month > user_month) {
            age = year - user_year;
        } else if (month < user_month) {
            age = year - user_year + 1;
        } else if (month == user_month) {
            if (day >= user_day) {
                age = year - user_year;
            } else {
                age = age = year - user_year + 1;
            }
        }
        return age;
    }

    public static void update(MemberUpdateDto updateDto, Member member) {
        if (updateDto.getName() != null) {
            member.setName(updateDto.getName());
        }
        if (updateDto.getGender() != null) {
            member.setGender(updateDto.getGender());
        }
        if (updateDto.getEmail() != null) {
            member.setEmail(updateDto.getEmail());
        }
        if (updateDto.getDob() != null) {
            member.setDob(updateDto.getDob());
            member.setAge(calcAge(updateDto.getDob()));
        }
        if (updateDto.getOccupation() != null) {
            member.setOccupation(updateDto.getOccupation());
        }
        if (updateDto.getPlace() != null) {
            member.setPlace(updateDto.getPlace());
        }
    }
}
