package Focus_Zandi.version1.web.repository;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class DBMemberRepository {

    private final EntityManager em;

    public void save(Member member) {em.persist(member);}

    public Member findOne(long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUserName(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public Member update(MemberUpdateDto updateDto, long id) {
        Member member = findOne(id);
        Member.update(updateDto, member);
        em.persist(member);
        return member;
    }
}
