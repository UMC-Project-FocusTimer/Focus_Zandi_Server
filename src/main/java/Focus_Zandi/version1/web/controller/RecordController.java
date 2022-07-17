package Focus_Zandi.version1.web.controller;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import Focus_Zandi.version1.web.service.MemberService;
import Focus_Zandi.version1.web.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final MemberService memberService;
    private final RecordService recordService;


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

    private String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        return context.getAuthentication().getName();
    }
}

