package com.blog.commuity.domain.user.presentation;

import com.blog.commuity.domain.user.application.UserService;
import com.blog.commuity.domain.user.dto.JoinReqDto;
import com.blog.commuity.domain.user.dto.JoinResDto;
import com.blog.commuity.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
