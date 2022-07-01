package hackertontest.test.web.controller;

import hackertontest.test.domain.Member;
import hackertontest.test.web.service.MemberService;
import hackertontest.test.web.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@RequiredArgsConstructor
public class TestingController {

    private final MemberService memberService;
    private final RecordService recordService;

    @GetMapping("/api/test")
    //http://localhost:8080/api/test?name=kim
    public Member test01(@RequestParam("name") String name) {
        Member member = new Member();
        member.setName(name);

        memberService.join(member);
        return member;
    }

    @GetMapping("/api/record/{memberId}")
    public RecordsDto ModelReceiver(@PathVariable long memberId, @RequestBody RecordsDto recordsDto) {
        recordService.save(recordsDto);
        return recordsDto;
    }

    @GetMapping("/api/member/{memberId}")
    //http://localhost:8080/api/member/1
    public Member showMember(@PathVariable long memberId) {
        Member findMember = memberService.findMember(memberId);
        return findMember;
    }
}