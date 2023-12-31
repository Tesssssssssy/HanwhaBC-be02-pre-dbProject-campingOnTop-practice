package com.example.campingontop.house.service;

import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.request.PutUpdateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PostCreateHouseDtoRes;
import com.example.campingontop.house.model.response.PutUpdateHouseDtoRes;
import com.example.campingontop.house.repository.HouseRepository;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
import com.example.campingontop.user.model.response.PutUpdateUserDtoRes;
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
                .content(postCreateHouseDtoReq.getContent())
                .price(postCreateHouseDtoReq.getPrice())
                .img(postCreateHouseDtoReq.getImg())
                .address(postCreateHouseDtoReq.getAddress())
                .latitude(postCreateHouseDtoReq.getLatitude())
                .longitude(postCreateHouseDtoReq.getLongitude())
                .maxUser(postCreateHouseDtoReq.getMaxUser())
                .hasAirConditioner(postCreateHouseDtoReq.getHasAirConditioner())
                .hasWashingMachine(postCreateHouseDtoReq.getHasWashingMachine())
                .build();

        House result = houseRepository.save(house);

        PostCreateHouseDtoRes response = PostCreateHouseDtoRes.builder()
                .id(result.getId())
                .name(result.getName())
                .content(result.getContent())
                .price(result.getPrice())
                .img(result.getImg())
                .address(result.getAddress())
                .latitude(result.getLatitude())
                .longitude(result.getLongitude())
                .maxUser(result.getMaxUser())
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
                    .maxUser(house.getMaxUser())
                    .hasAirConditioner(house.getHasAirConditioner())
                    .hasWashingMachine(house.getHasWashingMachine())
                    .build();
            return res;
        }
        return null;
    }

    public List<GetFindHouseDtoRes> findHouseList() {
        List<House> houses = houseRepository.findAll();
        List<GetFindHouseDtoRes> houseList = new ArrayList<>();

        for (House house : houses) {
            GetFindHouseDtoRes res = GetFindHouseDtoRes.builder()
                    .id(house.getId())
                    .name(house.getName())
                    .content(house.getContent())
                    .price(house.getPrice())
                    .img(house.getImg())
                    .address(house.getAddress())
                    .latitude(house.getLatitude())
                    .longitude(house.getLongitude())
                    .maxUser(house.getMaxUser())
                    .hasAirConditioner(house.getHasAirConditioner())
                    .hasWashingMachine(house.getHasWashingMachine())
                    .build();
            houseList.add(res);
        }
        return houseList;
    }

    public PutUpdateHouseDtoRes updateHouse(PutUpdateHouseDtoReq req, Long houseId) {
        Optional<House> result = houseRepository.findById(houseId);
        if (result.isPresent()) {
            House house = result.get();

            house.setName(req.getName());
            house.setContent(req.getContent());
            house.setPrice(req.getPrice());
            house.setImg(req.getImg());
            house.setAddress(req.getAddress());
            house.setLatitude(req.getLatitude());
            house.setLongitude(req.getLongitude());
            house.setMaxUser(req.getMaxUser());
            house.setIsActive(req.getIsActive());
            house.setHasAirConditioner(req.getHasAirConditioner());
            house.setHasWashingMachine(req.getHasWashingMachine());

            houseRepository.save(house);

            PutUpdateHouseDtoRes res = PutUpdateHouseDtoRes.builder()
                    .id(house.getId())
                    .name(house.getName())
                    .content(house.getContent())
                    .price(house.getPrice())
                    .img(house.getImg())
                    .address(house.getAddress())
                    .latitude(house.getLatitude())
                    .longitude(house.getLongitude())
                    .maxUser(house.getMaxUser())
                    .isActive(house.getIsActive())
                    .hasAirConditioner(house.getHasAirConditioner())
                    .hasWashingMachine(house.getHasWashingMachine())
                    .build();
            return res;
        }
        return null;
    }

    public void deleteHouse(Long houseId) {
        houseRepository.delete(House.builder().id(houseId).build());
    }
}
