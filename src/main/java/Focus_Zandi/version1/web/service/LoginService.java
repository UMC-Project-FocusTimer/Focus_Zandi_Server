package Focus_Zandi.version1.web.service;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.web.repository.DBMemberRepository;
import Focus_Zandi.version1.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

//    public Member login(String username, String pw) {
//        Member findMember = memberRepository.findByUsername(username);
//        if (findMember.getPassword().equals(pw)) return findMember;
//        else return null;
//    }
}
