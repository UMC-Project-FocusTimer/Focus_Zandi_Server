package Focus_Zandi.version1.web.controller;

import Focus_Zandi.version1.domain.Records;
import Focus_Zandi.version1.domain.dto.RecordReturnerDto;
import Focus_Zandi.version1.domain.dto.RecordsDto;
import Focus_Zandi.version1.web.service.RecordService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/send/data")
    public void receiveRecordV2(@RequestBody RecordsDto recordsDto, HttpServletRequest request) {
        String username = getUsername(request);
        recordService.save(username, recordsDto);
    }

    @GetMapping("/showRecords")
    public void showTodayRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsername(request);
        String timeStamp = LocalDate.now().toString();
        Records record = recordService.findRecordByTimeStamp(username, timeStamp);
        if (record == null) {
            response.setStatus(204);
            response.sendError(400, "No data");
            return;
        }
        RecordReturnerDto recordReturnerDto = new RecordReturnerDto(record);
        String json = new Gson().toJson(recordReturnerDto);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    // 지정 날짜로 검색
    // 항상 ?date=YYYY-MM-DD 형식을 지킬것
    @GetMapping("/showRecords/d")
    public void showRecordByDate(@RequestParam("date") String date, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsername(request);
        Records record = recordService.findRecordByTimeStamp(username, date);
        if (record == null) {
            response.setStatus(204);
            response.sendError(400, "No data");
            return;
        }
        RecordReturnerDto recordReturnerDto = new RecordReturnerDto(record);
        String json = new Gson().toJson(recordReturnerDto);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        return context.getAuthentication().getName();
    }
}

