package Focus_Zandi.version1.web.controller;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.dto.MemberRegisterDto;
import Focus_Zandi.version1.domain.dto.MemberUpdateDto;
import Focus_Zandi.version1.web.config.auth.PrincipalDetails;
import Focus_Zandi.version1.web.service.LoginService;
import Focus_Zandi.version1.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;
    private final LoginService loginService;

//    @PostMapping("/update")
//    public Member update(@RequestBody MemberUpdateDto updateDto, HttpServletRequest request) {
//        long memberId = getMemberId(request);
//        Member member = memberService.updateMember(updateDto, memberId);
//        return member;
//    }

    @PostMapping("/signUp")
    public String join(@RequestBody MemberRegisterDto registerDto) {
        memberService.join(registerDto);
        return "ok";
    }

    @GetMapping("/info")
    public String showMemberInfo() {
        System.out.println("memberService = " + memberService);
        return "Signed in";
    }


    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));


        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());

        SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        String name = context.getAuthentication().getName();
        System.out.println("LoginController.sessionInfo");
        System.out.println("name = " + name);


        System.out.println("context = " + context);

        return "세션 출력";

    }

}
