package com.blog.commuity.global.jwt;

import com.blog.commuity.domain.user.dto.LoginReqDto;
import com.blog.commuity.domain.user.dto.LoginResDto;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.global.util.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    //    원래 /login 하면 동작함
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            LoginReqDto loginReqDto = om.readValue(request.getInputStream(), LoginReqDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword());
            //loadByUsername 호출
            //jwt를 쓴다고 해도 컨트롤러 진입을 하면 시큐리티의 권한체크 인증체크의 도움을 받을 수 있다.
            //세션은 로그인할때만 사용한다.
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    //로그인 실패하면 반환한다.
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        CustomResponse.unAuthentication(response, "로그인 실패 ");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String jwtToken = Jwt.createAccessToken(user);
        String refreshToken = Jwt.createRefreshToken(user);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(Duration.ofDays(14))
                .path("/")
//                .secure(true)
//                .sameSite("None")
//                .domain("localhost:3000")
//                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
        LoginResDto loginResDto = new LoginResDto(user);
        CustomResponse.success(response, loginResDto);

    }
}
