package hackertontest.test.web.service;

import hackertontest.test.domain.Member;
import hackertontest.test.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public long join(Member member) {
        memberRepository.save(member);
        return member.getMemberId();
    }
    public Member findMember(long memberId) {
        return memberRepository.findOne(memberId);
    }

}
