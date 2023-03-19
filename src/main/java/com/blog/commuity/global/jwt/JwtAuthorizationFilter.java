package com.blog.commuity.global.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.global.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isHeaderVerify(request, response)) {
            try {
                String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace(Jwt.PREFIX, "");
                User user = Jwt.accessTokenVerify(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } catch (TokenExpiredException e) {
                CustomResponse.unAuthentication(response, "토큰이 만료됨");
            } catch (Exception e) {
                CustomResponse.unAuthentication(response, "토큰이 없음");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isHeaderVerify(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !(header == null) && header.startsWith(Jwt.PREFIX);
    }


}
