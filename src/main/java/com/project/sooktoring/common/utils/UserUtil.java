package com.project.sooktoring.common.utils;

import com.project.sooktoring.common.exception.CustomException;
import com.project.sooktoring.auth.domain.User;
import com.project.sooktoring.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.project.sooktoring.common.exception.ErrorCode.NOT_FOUND_USER;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    }
}
