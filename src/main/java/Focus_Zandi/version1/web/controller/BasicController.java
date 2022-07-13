package Focus_Zandi.version1.web.controller;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.MemberReturnerDto;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import Focus_Zandi.version1.web.service.MemberService;
import Focus_Zandi.version1.web.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BasicController {
    private final RecordService recordService;
    private final MemberService memberService;

    @GetMapping("/")
    public MemberReturnerDto loginHandler(HttpServletRequest request) {
        String username = getUsername(request);
        Member member = memberService.findMemberByUserName(username);
        MemberReturnerDto memberReturnerDto = new MemberReturnerDto(member);
        return memberReturnerDto;
    }

    @GetMapping("/showMember")
    public Member showMemberV2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsername(request);

        return memberService.findMemberByUserName(username);
    }

    @PostMapping("/send/data")
    public void receiveRecordV2(@RequestBody RecordsDto recordsDto, HttpServletRequest request) {
        String username = getUsername(request);
        recordService.save(username, recordsDto);
    }

    @GetMapping("/showRecords")
    public Records showTodayRecord(HttpServletRequest request) {
        String username = getUsername(request);
        Member member = memberService.findMemberByUserName(username);
        String timeStamp = LocalDate.now().toString();
        return recordService.findRecordByTimeStamp(member, timeStamp);
    }

    // 지정 날짜로 검색
    // 항상 YYYY-MM-DD 형식을 지킬것
    // 아직 구현 안함
//    @GetMapping("/showRecords/date")
//    public Records showRecordByDate(@PathVariable long memberId, @RequestParam("date") String date, HttpServletRequest request) {
//        long id = getIdByuserName(request);
//        String timeStamp = LocalDate.now().toString();
//        return recordService.findRecordByTimeStamp(id, timeStamp);
//    }

    @GetMapping("/test01")
    public String ok() {
        System.out.println("BasicController.ok");
        return "ok";
    }

    private String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        return context.getAuthentication().getName();
    }

    private long getIdByuserName(HttpServletRequest request) {
        String username = getUsername(request);
        Member memberByUserName = memberService.findMemberByUserName(username);
        return memberByUserName.getId(); // 이거 너무 비효율적이지 않나
    }
}
