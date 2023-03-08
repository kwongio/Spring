package com.blog.commuity.domain.user.application;

import com.blog.commuity.domain.user.dto.JoinReqDto;
import com.blog.commuity.domain.user.dto.JoinResDto;
import com.blog.commuity.domain.user.dto.UserInfoResDto;
import com.blog.commuity.domain.user.entity.User;
import com.blog.commuity.domain.user.exception.AlreadyExistUserException;
import com.blog.commuity.domain.user.exception.UserNotFoundException;
import com.blog.commuity.domain.user.repository.UserQueryRepositoryImpl;
import com.blog.commuity.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserQueryRepositoryImpl userQueryRepository;


    public JoinResDto join(JoinReqDto joinDto) {
        Optional<User> username = userRepository.findByUsername(joinDto.getUsername());
        Optional<User> email = userRepository.findByEmail(joinDto.getEmail());
        if (username.isPresent() || email.isPresent()) {
            throw new AlreadyExistUserException();
        }
        User user = userRepository.save(joinDto.toEntity(passwordEncoder));
        return new JoinResDto(user);
    }

    public UserInfoResDto getInfo(Long id) {
        User user = userQueryRepository.findByUserId(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new UserInfoResDto(user);
    }


}
