package com.blog.commuity.domain.user.presentation;

import com.blog.commuity.domain.user.application.UserService;
import com.blog.commuity.domain.user.dto.JoinReqDto;
import com.blog.commuity.domain.user.dto.JoinResDto;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody JoinReqDto joinResDto) {
        JoinResDto join = userService.join(joinResDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입성공", join), HttpStatus.CREATED);
    }

    @GetMapping("/admin/s")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("어드민");
    }


    @Operation(summary = "유저 한명 정보 가져오기", description = "유저정보")
    @GetMapping("/myPage")
    public ResponseEntity<?> getInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getInfo(user.getId()));
    }





}
