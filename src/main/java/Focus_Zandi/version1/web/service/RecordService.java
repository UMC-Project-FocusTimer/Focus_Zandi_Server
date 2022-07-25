package Focus_Zandi.version1.web.service;

import Focus_Zandi.version1.domain.Followers;
import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.FriendRankDto;
import Focus_Zandi.version1.domain.dto.MonthlyRecordsDto;
import Focus_Zandi.version1.domain.dto.MyFollowersDto;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import Focus_Zandi.version1.web.repository.FollowersRepository;
import Focus_Zandi.version1.web.repository.MemberRepository;
import Focus_Zandi.version1.web.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class RecordService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final FollowersRepository followersRepository;

    public void save(String username, RecordsDto recordsDto) {
        Member member = memberRepository.findByUsername(username);
        Records records = Records.createRecords(member, recordsDto);
        recordRepository.save(records);
    }

    public Records findRecordByTimeStamp(String username, String date) {
        Member member = memberRepository.findByUsername(username);
        return recordRepository.findRecordByTimeStamp(member, date);
    }

    public List<Integer> findMonthly(String month, String username) {
        Member member = memberRepository.findByUsername(username);
        return recordRepository.findAllByMonth(month, member);
    }

    public List<MonthlyRecordsDto> findMonthlyV2(String month, String username) {
        Member member = memberRepository.findByUsername(username);
        return recordRepository.findAllByMonthV2(month, member);
    }

    public List<MyFollowersDto> dailyRanks(String username) {
        Member member = memberRepository.findByUsername(username);
        List<String> followers = followersRepository.findFollowers(member);
        return recordRepository.findFollowersDailyRecords(followers, member);
    }
}
