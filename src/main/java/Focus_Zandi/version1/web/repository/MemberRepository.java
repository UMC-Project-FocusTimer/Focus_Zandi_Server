package Focus_Zandi.version1.web.repository;

import Focus_Zandi.version1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //findBy는 규칙
    // select * from user where username = ?;
    //라는 sql 자동 생성

    public Member findByUsername(String username);
}
