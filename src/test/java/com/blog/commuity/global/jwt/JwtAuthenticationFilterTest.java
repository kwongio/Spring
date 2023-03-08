package com.blog.commuity.global.jwt;

import com.blog.commuity.domain.user.dto.LoginReqDto;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JwtAuthenticationFilterTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    @Test
    public void setup() throws IOException {
        User user = new User("1234", passwordEncoder.encode("1234"), "1234@1234", "1234");
        userRepository.save(user);
    }

    @Test
    void attemptAuthentication() throws Exception {
        LoginReqDto loginReqDto = new LoginReqDto("1234", "1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(loginReqDto);
        ResultActions resultActions = mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON).content(s));
        String jwtToken = resultActions.andReturn().getResponse().getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
        System.out.println(jwtToken);
        resultActions.andExpect(status().isOk());
        assertThat(jwtToken != null);
        assertThat(jwtToken.startsWith(Jwt.PREFIX));
        resultActions.andExpect(jsonPath("$.data.username").value("1234"));

    }

    @Test
    void unsuccessfulAuthentication() {

    }

    @Test
    void successfulAuthentication() {
    }
}