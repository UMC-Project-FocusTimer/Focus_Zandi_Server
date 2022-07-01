package hackertontest.test.web.repository;

import hackertontest.test.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 나중에 jpa 도입하면 사라짐 지금은 메모리저장소라 사용중

    public Member save(Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

    public Member findOne(long id) {
        return store.get(id);
    }

}

