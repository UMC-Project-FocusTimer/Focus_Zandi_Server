package hackertontest.test.web.controller;

import hackertontest.test.domain.Member;
import hackertontest.test.web.service.MemberService;
import hackertontest.test.web.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

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

//    @GetMapping("/api/record/{memberId}")
    //http://localhost:8080/api/record/1?s_time=1&e_time=10
    public RecordsDto test02(@PathVariable long memberId, @RequestParam int s_time, @RequestParam int e_time) {
        RecordsDto recordsDto = new RecordsDto();
        recordsDto.setMemberId(memberId);

        recordService.save(recordsDto);
        return recordsDto;
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
