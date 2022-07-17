package Focus_Zandi.version1.web.service;

import Focus_Zandi.version1.domain.Followers;
import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.domain.dto.MemberRegisterDto;
import Focus_Zandi.version1.web.repository.FollowersRepository;
import Focus_Zandi.version1.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final FollowersRepository followersRepository;
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

    public void makeFollow(String followeeName, String username) {
        Member followee = memberRepository.findByUsername(followeeName);
        Member follower = memberRepository.findByUsername(username);

        Followers followerShip = Followers.createFollowerShip(followee.getId(), follower);

        followersRepository.makeFollow(followerShip);
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

    public void makeUnFollow(String followeeName, String username) {
        long followeeId = memberRepository.findByUsername(followeeName).getId();
        Member follower = memberRepository.findByUsername(username);
        followersRepository.unFollow(followeeId, follower);
    }
}
