package com.example.campingontop.user.service;

import com.example.campingontop.user.model.User;
import com.example.campingontop.user.model.request.PostCreateUserDtoReq;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
import com.example.campingontop.user.model.response.PostCreateUserDtoRes;
import com.example.campingontop.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PostCreateUserDtoRes createUser(PostCreateUserDtoReq req) {
        User user = User.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .name(req.getName())
                .nickName(req.getNickName())
                .phoneNum(req.getPhoneNum())
                .gender(req.getGender())
                .birthDay(req.getBirthDay())
                .build();

        User result = userRepository.save(user);

        PostCreateUserDtoRes res = PostCreateUserDtoRes.builder()
                .id(result.getId())
                .email(result.getEmail())
                .name(result.getName())
                .nickName(result.getNickName())
                .gender(result.getGender())
                .birthDay(result.getBirthDay())
                .createdAt(result.getCreatedAt())
                .build();

        return res;
    }

    public GetFindUserDtoRes findUserById(Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();
            GetFindUserDtoRes res = GetFindUserDtoRes.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .phoneNum(user.getPhoneNum())
                    .gender(user.getGender())
                    .birthDay(user.getBirthDay())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();
            return res;
        }
        return null;
    }
}
