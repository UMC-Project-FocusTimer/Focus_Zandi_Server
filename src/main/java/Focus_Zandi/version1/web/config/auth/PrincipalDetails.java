package Focus_Zandi.version1.web.config.auth;

// 시큐리티가 /login 주소요청이 오면 낚아채서 로그인 지행
// 진행완료가 되면 시큐리티 session 에 넣어줘야함 (Security ContextHolder)
// 시큐리티가 가지고 있는 세션에 들어갈 수 있는 오브젝트가 정해졌다.
// Authentication 타입 객체
// 이 객체 안에는 유저정보가 있어야 한다.
// 유저 오브젝트 타입 => UserDetails 타입 객체

import Focus_Zandi.version1.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private Member member; // 콤포지션

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    //해당 유저의 권한 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
