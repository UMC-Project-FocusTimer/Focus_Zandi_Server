package Focus_Zandi.version1.web.controller;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.dto.DetailsDto;
import Focus_Zandi.version1.domain.dto.FolloweeNameDto;
import Focus_Zandi.version1.domain.dto.MemberReturnerDto;
import Focus_Zandi.version1.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/getUserInfo")
    public MemberReturnerDto loginHandler(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String username = getUsername(request);
        Member member = memberService.findMemberByUserName(username);
        MemberReturnerDto memberReturnerDto = new MemberReturnerDto(member, member.getMemberDetails());
        return memberReturnerDto;
    }

    @GetMapping("/showMember") // 멤버 객체 내용 전체 삭제 예정
    public Member showMemberV2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUsername(request);

        return memberService.findMemberByUserName(username);
    }

    @PostMapping("/doFollow")
    public int followMember(@RequestBody FolloweeNameDto followeeName, HttpServletResponse response, HttpServletRequest request) {
        memberService.makeFollow(followeeName.getFolloweeName(), getUsername(request));
        return response.getStatus();
    }

    @PostMapping("/undoFollow")
    public int unfollowMember(@RequestBody FolloweeNameDto followeeName, HttpServletResponse response, HttpServletRequest request) {
        memberService.makeUnFollow(followeeName.getFolloweeName(), getUsername(request));
        return response.getStatus();
    }

    @PostMapping("/register")
    public int getDetails(@RequestBody DetailsDto detailsDto, HttpServletRequest request, HttpServletResponse response) {
        memberService.join(detailsDto, getUsername(request));
        return response.getStatus();
    }

    private String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        return context.getAuthentication().getName();
    }
}
