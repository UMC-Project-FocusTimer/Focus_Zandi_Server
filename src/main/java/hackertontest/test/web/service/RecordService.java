package hackertontest.test.web.service;

import hackertontest.test.domain.Member;
import hackertontest.test.domain.Records;
import hackertontest.test.web.controller.RecordsDto;
import hackertontest.test.web.repository.MemberRepository;
import hackertontest.test.web.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    public void save(RecordsDto recordsDto) {
        // 멤버가 없으면 어쩌지? 오류처리?
        recordRepository.save(recordsDto);
    }

    public Records findRecordByMemberId(long memberId) {
        return recordRepository.findRecordByMemberId(memberId);
    }

    public Records findRecordByTimeStamp(long memberId, String timeStamp) {
        return recordRepository.findRecordByTimeStamp(memberId, timeStamp);
    }
}
