package hackertontest.test.web.controller;

import hackertontest.test.domain.Member;
import hackertontest.test.domain.Records;
import hackertontest.test.web.repository.MemberRepository;
import hackertontest.test.web.service.MemberService;
import hackertontest.test.web.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasicController {
    private final RecordService recordService;
    private final MemberService memberService;

    //테스트용 멤버 데이터 3개
    @PostConstruct
    public void makeMember() {
        Member member1 = new Member();
        member1.setMemberId(1);
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setMemberId(2);
        member2.setName("Lee");

        Member member3 = new Member();
        member3.setMemberId(3);
        member3.setName("Park");

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
    }

    //멤버 데이터 반환 api
    @GetMapping("/showMember/{memberId}")
    public Member showMember(@PathVariable long memberId) {
        return memberService.findMember(memberId);
    }

    //데이터 받기 api
    @PostMapping("/send/data")
    public void receiveRecord(@RequestBody RecordsDto recordsDto) {
        recordService.save(recordsDto);
    }

    //RecordData 반환
    //임시반환이라 일단은 단순하게 memberId로만 찾음
    //추후 기능이 추가되면, TimeStamp를 가지고 찾을 예정
//    @GetMapping("/showRecords/{memberId}")
    public Records showRecords(@PathVariable long memberId) {
        return recordService.findRecordByMemberId(memberId);
    }

//    @GetMapping("/showRecords/{memberId}")
    public Records showRecordsByTimeStamp(@PathVariable long memberId, @RequestParam String timeStamp) {
        return recordService.findRecordByMemberId(memberId);
    }

    @GetMapping("/showRecords/{memberId}")
    public Records showTodayRecord(@PathVariable long memberId) {
        String timeStamp = LocalDate.now().toString();
        return recordService.findRecordByTimeStamp(memberId, timeStamp);
    }

    // 지정 날짜로 검색
    // 항상 YYYY-MM-DD 형식을 지킬것
    @GetMapping("/showRecords/{memberId}/date")
    public Records showRecordByDate(@PathVariable long memberId, @RequestParam("date") String date) {
        String timeStamp = LocalDate.now().toString();
        return recordService.findRecordByTimeStamp(memberId, timeStamp);
    }
}
