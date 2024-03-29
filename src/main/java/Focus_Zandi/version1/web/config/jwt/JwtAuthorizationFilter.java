package Focus_Zandi.version1.web.config.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Focus_Zandi.version1.domain.Member;
import Focus_Zandi.version1.web.config.auth.PrincipalDetails;
import Focus_Zandi.version1.web.repository.MemberRepository;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

<<<<<<< HEAD
=======
        Enumeration<String> headerNames = request.getHeaderNames();
        while (true) {
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println("name = " + name);
            System.out.println("header = " + header);

            if(!headerNames.hasMoreElements()) break;
        }

>>>>>>> e1c7a6324cba5cafe5c9aca6c3a6a607574444fa
        String access_token = request.getHeader("ACCESS_TOKEN");
        String refresh_token = request.getHeader("REFRESH_TOKEN");
        String authorization = request.getHeader("Authorization");

<<<<<<< HEAD
=======
        System.out.println("access_token = " + access_token);
        System.out.println("refresh_token = " + refresh_token);
        System.out.println("authorization = " + authorization);

>>>>>>> e1c7a6324cba5cafe5c9aca6c3a6a607574444fa
//        //header에 있는 jwt bearer 토큰 검증
//        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        //bearer 부분 자르기
//        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        // 내가 SecurityContext에 집적접근해서 세션을 만들때 자동으로 UserDetailsService에 있는
        // loadByUsername이 호출됨.
        String username = null;

        try {
            username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(authorization)
                    .getClaim("username").asString();
        } catch (TokenExpiredException e) {
            String restoreUsername = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(refresh_token)
                    .getClaim("username").asString();
            if (restoreUsername != null) {
                Member member = memberRepository.findByUsername(restoreUsername);
                String accessToken = CreateJwt.createAccessToken(member);
                String refreshToken = CreateJwt.createRefreshToken(member, accessToken);

                response.setHeader("ACCESS_TOKEN", accessToken);
                response.setHeader("REFRESH_TOKEN", refreshToken);
            }
        }

        if (username != null) {
            Member member = memberRepository.findByUsername(username);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, // 나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                    null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                    principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}
