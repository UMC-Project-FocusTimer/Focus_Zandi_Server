package hackertontest.test.web.repository;

import hackertontest.test.domain.Records;
import hackertontest.test.web.controller.RecordsDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RecordRepository {

    private static long sequence = 0L;
    private static final Map<Long, Records> store = new HashMap<>();

    //레코드 Dto를 받아서, 이를 Records로 변환하고 db에 저장
    public Records save(RecordsDto recordsDto) {
        Records records = new Records();

        records.setMemberId(recordsDto.getMemberId());
        List<Integer> dataHolder = recordsDto.getConcentratedTime();
        records.setTimeStamp(recordsDto.getTimeStamp());

        if (dataHolder.isEmpty()) {
            // 작동할 일이 있을까? (validation 추가하면 없애도 될듯0
            records.setBrokenCounter(0);
            records.setMaxConcentrationTime(0);
        } else {
            int max = 0;
            int sum = 0;
            for (Integer integer : dataHolder) {
                sum += integer;
                if (integer > max) max = integer;
            }
            records.setBrokenCounter(dataHolder.size());
            records.setMaxConcentrationTime(max);
            records.setTotal_time(sum);
        }

        records.setRecordId(++sequence);
        store.put(records.getRecordId(), records);
        return records;
    }

    public Records findRecordByMemberId(long memberId) {
        Collection<Records> values = store.values();
        for (Records value : values) {
            if (value.getMemberId() == memberId) {
                return value;
            }
        }
        return null;
    }

    public Records findRecordByTimeStamp(long memberId, String timeStamp) {
        Collection<Records> values = store.values();
        for (Records value : values) {
            String valueTimeStamp = value.getTimeStamp();
            String cut = valueTimeStamp.substring(0, 10);
            if ((value.getMemberId() == memberId) && (cut.equals(timeStamp))) {
                return value;
            }
        }
        // 아무것도 안돌아오면 exception 처리 후 안내메시지를 올린다?
        return null;
    }
}
