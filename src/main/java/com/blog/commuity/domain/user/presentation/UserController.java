package com.blog.commuity.domain.user.presentation;

import com.blog.commuity.domain.user.application.UserService;
import com.blog.commuity.domain.user.dto.JoinReqDto;
import com.blog.commuity.domain.user.dto.JoinResDto;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.global.jwt.Jwt;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입")

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody JoinReqDto joinResDto) {
        userService.join(joinResDto);
        return ResponseEntity.ok("회원가입완료");
    }


    @Operation(summary = "user정보와 postList정보 가져오기")
    @GetMapping("/myPage")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getInfo(user.getId()));
    }


//    @GetMapping("/users")
//    public ResponseEntity<?> getUserList() {
//        return ResponseEntity.ok(userService.getUserList());
//    }

    @Operation(summary = "리프레쉬 토큰 검증하고 엑세스토큰 반환")
    @GetMapping("/refresh")
    public ResponseEntity<?> getInfo(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                String refreshToken = cookie.getValue();
                User user = Jwt.refreshTokenVerify(refreshToken);
                String accessToken = Jwt.createAccessToken(user);
                response.setHeader(HttpHeaders.AUTHORIZATION, accessToken);
            }
        }
        return ResponseEntity.ok("토큰 반환 완료");
    }


}
