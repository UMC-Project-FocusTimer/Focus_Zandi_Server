package Focus_Zandi.version1.web.repository;

import Focus_Zandi.version1.domain.Followers;
import Focus_Zandi.version1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class FollowersRepository {

    private final EntityManager em;

    public void makeFollow(Followers followers) {;
        em.persist(followers);
    }

    public void unFollow(long followeeId, Member follower) {
        String jpql = "delete from Followers m where m.followeeId =:followeeId and m.member = :follower";
        Query query = em.createQuery(jpql)
                .setParameter("followeeId", followeeId)
                .setParameter("follower", follower);
        query.executeUpdate();
    }
}
