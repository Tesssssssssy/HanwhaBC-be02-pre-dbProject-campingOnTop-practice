package com.example.campingontop.house.service;

import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PostCreateHouseDtoRes;
import com.example.campingontop.house.repository.HouseRepository;
import com.example.campingontop.orders.model.PaymentHouses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final Logger log = LoggerFactory.getLogger(HouseService.class);
    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public PostCreateHouseDtoRes createHouse(PostCreateHouseDtoReq postCreateHouseDtoReq) {
        House house = House.builder()
                .name(postCreateHouseDtoReq.getName())
                .address(postCreateHouseDtoReq.getAddress())
                .latitude(postCreateHouseDtoReq.getLatitude())
                .longitude(postCreateHouseDtoReq.getLongitude())
                .content(postCreateHouseDtoReq.getContent())
                .max_user(postCreateHouseDtoReq.getMax_user())
                .price(postCreateHouseDtoReq.getPrice())
                .img(postCreateHouseDtoReq.getImg())
                .hasAirConditioner(postCreateHouseDtoReq.getHasAirConditioner())
                .hasWashingMachine(postCreateHouseDtoReq.getHasWashingMachine())
                .build();

        House result = houseRepository.save(house);

        PostCreateHouseDtoRes response = PostCreateHouseDtoRes.builder()
                .id(result.getId())
                .name(result.getName())
                .price(result.getPrice())
                .img(result.getImg())
                .address(result.getAddress())
                .latitude(result.getLatitude())
                .longitude(result.getLongitude())
                .max_user(result.getMax_user())
                .hasAirConditioner(result.getHasAirConditioner())
                .hasWashingMachine(result.getHasWashingMachine())
                .createdAt(result.getCreatedAt())
                .build();

        return response;
    }

    public GetFindHouseDtoRes findHouseById(Long houseId) {
        Optional<House> result = houseRepository.findById(houseId);
        if (result.isPresent()) {
            House house = result.get();
            GetFindHouseDtoRes res = GetFindHouseDtoRes.builder()
                    .id(house.getId())
                    .name(house.getName())
                    .content(house.getContent())
                    .price(house.getPrice())
                    .img(house.getImg())
                    .address(house.getAddress())
                    .latitude(house.getLatitude())
                    .longitude(house.getLongitude())
                    .max_user(house.getMax_user())
                    .hasAirConditioner(house.getHasAirConditioner())
                    .hasWashingMachine(house.getHasWashingMachine())
                    .build();
            return res;
        }
        return null;
    }

    public Integer getTotalPrice(PaymentHouses datas){

        List<Long> productIds = new ArrayList<>();
        for (House house: datas.getHouses()) {
            productIds.add(house.getId());
        }

        List<House> houses = houseRepository.findAllById(productIds);

        Integer totalPrice = 0;
        for (House house: houses) {
            totalPrice += house.getPrice();
        }

        return  totalPrice;
    }

}
