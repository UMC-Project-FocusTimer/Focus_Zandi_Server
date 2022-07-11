package Focus_Zandi.version1.web.service;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.dto.MemberRegisterDto;
import Focus_Zandi.version1.domain.dto.MemberUpdateDto;
import Focus_Zandi.version1.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(MemberRegisterDto registerDto) {
        Member member = Member.createMember(registerDto);
        validateDuplicateMember(member);
        String encode = passwordEncoder.encode(member.getPassword());
        member.setPassword(encode); // 비밀번호 인코딩 후 저장
        memberRepository.save(member);
    }

    public Member findMemberByUserName(String name) {
        return memberRepository.findByUsername(name);
    }

    //추후 수정
//    public Member updateMember(MemberUpdateDto updateDto, long memberId) {
//        Member updatedMember = memberRepository.update(updateDto, memberId);
//        return updatedMember;
//    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUsername(member.getUsername());
        if (findMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }
}
