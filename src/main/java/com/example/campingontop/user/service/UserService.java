package com.example.campingontop.user.service;

import com.example.campingontop.aws.service.S3Service;
import com.example.campingontop.enums.Gender;
import com.example.campingontop.exception.ErrorCode;
import com.example.campingontop.exception.entityException.HouseException;
import com.example.campingontop.exception.entityException.UserException;
import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.response.PostSetHouseImgDtoRes;
import com.example.campingontop.user.model.User;
import com.example.campingontop.user.model.request.PostCreateUserDtoReq;
import com.example.campingontop.user.model.request.PostSetUserImgDtoReq;
import com.example.campingontop.user.model.request.PutUpdateUserDtoReq;
import com.example.campingontop.user.model.response.*;
import com.example.campingontop.user.repository.UserRepository;
import com.example.campingontop.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
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
    private S3Service s3Service;

    public UserService(UserRepository userRepository, S3Service s3Service) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    public PostCreateUserDtoRes createUser(PostCreateUserDtoReq request) {
        User user = User.toEntity(request);
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

    public PostSetUserImgDtoRes setUserImg(PostSetUserImgDtoReq request, Long userId) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();

            if (request.getImg() != null) {
                String savePath = ImageUtils.makeUserImagePath(request.getImg().getOriginalFilename());
                savePath = s3Service.uploadFile(request.getImg(), savePath);
                user.setImg(savePath);
            } else {
                throw new UserException(ErrorCode.IMAGE_EMPTY);
            }
            user = userRepository.save(user);
            return PostSetUserImgDtoRes.toDto(user);
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.delete(User.builder().id(userId).build());
    }

}
