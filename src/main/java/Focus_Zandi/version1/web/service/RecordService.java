package Focus_Zandi.version1.web.service;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import Focus_Zandi.version1.web.repository.MemberRepository;
import Focus_Zandi.version1.web.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class RecordService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    public void save(String username, RecordsDto recordsDto) {
        Member member = memberRepository.findByUsername(username);
        Records records = Records.createRecords(member, recordsDto);
        recordRepository.save(records);
    }

    public Records findRecordByTimeStamp(String username, String date) {
        Member member = memberRepository.findByUsername(username);
        return recordRepository.findRecordByTimeStamp(member, date);
    }
}
