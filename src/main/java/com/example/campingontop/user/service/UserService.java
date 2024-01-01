package com.example.campingontop.user.service;

import com.example.campingontop.enums.Gender;
import com.example.campingontop.user.model.User;
import com.example.campingontop.user.model.request.PostCreateUserDtoReq;
import com.example.campingontop.user.model.request.PutUpdateUserDtoReq;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
import com.example.campingontop.user.model.response.GetUserWithHouseLikeDtoRes;
import com.example.campingontop.user.model.response.PostCreateUserDtoRes;
import com.example.campingontop.user.model.response.PutUpdateUserDtoRes;
import com.example.campingontop.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                .gender(Gender.fromValue(req.getGender()))
                .birthDay(req.getBirthDay())
                .build();

        user = userRepository.save(user);

        return PostCreateUserDtoRes.toDto(user);
    }

    public GetFindUserDtoRes findUserById(Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();

            return GetFindUserDtoRes.toDto(user);
        }
        return null;
    }

    public List<GetFindUserDtoRes> findUserList() {
        List<User> users = userRepository.findAll();
        List<GetFindUserDtoRes> userList = new ArrayList<>();

        for (User user : users) {
            GetFindUserDtoRes res = GetFindUserDtoRes.toDto(user);
            userList.add(res);
        }
        return userList;
    }

    public PutUpdateUserDtoRes updateUser(PutUpdateUserDtoReq req, Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();

            user.setPassword(req.getPassword());
            user.setName(req.getName());
            user.setNickName(req.getNickName());
            user.setPhoneNum(req.getPhoneNum());
            user.setGender(Gender.fromValue(req.getGender()));

            user = userRepository.save(user);

            PutUpdateUserDtoRes res = PutUpdateUserDtoRes.toDto(user);
            return res;
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.delete(User.builder().id(userId).build());
    }

}
