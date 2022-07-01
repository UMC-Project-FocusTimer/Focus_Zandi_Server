package hackertontest.test.domain;

import hackertontest.test.web.controller.RecordsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Records {

    private long memberId;
    private long recordId;

    private int brokenCounter;
    private int maxConcentrationTime;
    private int total_time;

    private String timeStamp;

    // 비즈니스 로직 //
//    public Records createRecords(RecordsDto recordsDto) {
//        Records records = new Records();
//
//        records.setMemberId(recordsDto.getMemberId());
//        List<Integer> dataHolder = recordsDto.getConcentratedTime();
//        records.setTimeStamp(recordsDto.getTimeStamp());
//
//        if (dataHolder.isEmpty()) {
//            // 작동할 일이 있을까? (validation 추가하면 없애도 될듯0
//            records.setBrokenCounter(0);
//            records.setMaxConcentrationTime(0);
//        } else {
//            int max = 0;
//            int sum = 0;
//            for (Integer integer : dataHolder) {
//                sum += integer;
//                if (integer > max) max = integer;
//            }
//            records.setBrokenCounter(dataHolder.size());
//            records.setMaxConcentrationTime(max);
//            records.setTotal_time(sum);
//        }
//
//        return records;
//    }
}
