package Focus_Zandi.version1.web.repository;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RecordRepository {

    private final EntityManager em;

    //레코드 Dto를 받아서, 이를 Records로 변환하고 db에 저장
    public Records save(Records records) {
        em.persist(records);
        return records;
    }

//    public Records findRecordByMemberId(long memberId) {
////        Records records = em.find(Records.class, memberId); // 이거는 pk만 가능
//        List<Records> resultList = em.createQuery("select r from Records r where r.member = :memberId", Records.class)
//                .setParameter("memberId", memberId)
//                .getResultList();
//        for (Records records : resultList) {
//            long recordId = records.getRecordId();
//            System.out.println("recordId = " + recordId);
//        }
//        Records records = resultList.get(0);
//        return records;
//    }

    public Records findRecordByTimeStamp(Member member, String timeStamp) {
        List<Records> resultList = em.createQuery("select r from Records r where r.member = :member", Records.class)
                .setParameter("member", member)
                .getResultList();
        for (Records value : resultList) {
            String valueTimeStamp = value.getTimeStamp();
            String cut = valueTimeStamp.substring(0, 10);
            if ((value.getMember().getId() == member.getId()) && (cut.equals(timeStamp))) {
                return value;
            }
        }
        // 아무것도 안돌아오면 exception 처리 후 안내메시지를 올린다?
        return null;
    }
}
