package com.example.campingontop.houseLike.service;

import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.repository.HouseRepository;
import com.example.campingontop.houseLike.model.HouseLike;
import com.example.campingontop.houseLike.model.dto.request.PostCreateHouseLikeDtoReq;
import com.example.campingontop.houseLike.repository.HouseLikeRepository;
import com.example.campingontop.user.model.User;
import com.example.campingontop.user.model.response.GetUserWithHouseLikeDtoRes;
import com.example.campingontop.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HouseLikeService {
    private HouseLikeRepository houseLikeRepository;
    private UserRepository userRepository;
    private HouseRepository houseRepository;

    public HouseLikeService(HouseLikeRepository houseLikeRepository, UserRepository userRepository, HouseRepository houseRepository) {
        this.houseLikeRepository = houseLikeRepository;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
    }

    public void createHouseLike(PostCreateHouseLikeDtoReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + req.getUserId()));

        House house = houseRepository.findById(req.getHouseId())
                .orElseThrow(() -> new EntityNotFoundException("House not found with ID: " + req.getHouseId()));

        HouseLike houseLike = HouseLike.builder()
                .user(user)
                .house(house)
                .build();

        houseLikeRepository.save(houseLike);
    }

    public List<GetUserWithHouseLikeDtoRes> findHouseLikeByUserId(Long userId) {
        List<HouseLike> houseLikes = houseLikeRepository.findByUserId(userId);
        List<GetUserWithHouseLikeDtoRes> result = new ArrayList<>();

        if (!houseLikes.isEmpty()) {
            for (HouseLike houseLike : houseLikes) {
                User user = houseLike.getUser();
                House house = houseLike.getHouse();

                GetFindHouseDtoRes houseDto = GetFindHouseDtoRes.toDto(house);

                GetUserWithHouseLikeDtoRes userDto = GetUserWithHouseLikeDtoRes.builder()
                        .id(houseLike.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .nickName(user.getNickName())
                        .phoneNum(user.getPhoneNum())
                        .gender(user.getGender().name())
                        .birthDay(user.getBirthDay())
                        .createdAt(houseLike.getCreatedAt())
                        .updatedAt(houseLike.getUpdatedAt())
                        .likedHouses(Collections.singletonList(houseDto))
                        .build();

                result.add(userDto);
            }
            return result;
        }
        return null;
    }
}
