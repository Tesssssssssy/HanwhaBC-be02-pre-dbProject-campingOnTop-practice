package com.example.campingontop.house.service;

import com.example.campingontop.aws.service.S3Service;
import com.example.campingontop.exception.ErrorCode;
import com.example.campingontop.exception.entityException.HouseException;
import com.example.campingontop.house.model.House;
import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.request.PostSetHouseImgDtoReq;
import com.example.campingontop.house.model.request.PutUpdateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PostCreateHouseDtoRes;
import com.example.campingontop.house.model.response.PostSetHouseImgDtoRes;
import com.example.campingontop.house.model.response.PutUpdateHouseDtoRes;
import com.example.campingontop.house.repository.HouseRepository;
import com.example.campingontop.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
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
    private S3Service s3Service;

    public HouseService(HouseRepository houseRepository, S3Service s3Service) {
        this.houseRepository = houseRepository;
        this.s3Service = s3Service;
    }

    public PostCreateHouseDtoRes createHouse(PostCreateHouseDtoReq request) {
        Optional<House> result = houseRepository.findByName(request.getName());
        if (result.isPresent()) {
            throw new HouseException(ErrorCode.DUPLICATED_HOUSE, String.format("숙소 이름: %s", request.getName()));
        }

        House house = House.toEntity(request);
        house = houseRepository.save(house);

        PostCreateHouseDtoRes response = PostCreateHouseDtoRes.toDto(house);

        return response;
    }

    public GetFindHouseDtoRes findHouseById(Long houseId) {
        Optional<House> result = houseRepository.findById(houseId);
        if (result.isPresent()) {
            House house = result.get();
            GetFindHouseDtoRes res = GetFindHouseDtoRes.toDto(house);
            return res;
        }
        return null;
    }

    public List<GetFindHouseDtoRes> findHouseList() {
        List<House> houses = houseRepository.findAll();
        List<GetFindHouseDtoRes> houseList = new ArrayList<>();

        for (House house : houses) {
            GetFindHouseDtoRes res = GetFindHouseDtoRes.toDto(house);
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
            house.setAddress(req.getAddress());
            house.setLatitude(req.getLatitude());
            house.setLongitude(req.getLongitude());
            house.setMaxUser(req.getMaxUser());
            house.setIsActive(req.getIsActive());
            house.setHasAirConditioner(req.getHasAirConditioner());
            house.setHasWashingMachine(req.getHasWashingMachine());

            house = houseRepository.save(house);

            PutUpdateHouseDtoRes res = PutUpdateHouseDtoRes.toDto(house);
            return res;
        }
        return null;
    }

    public PostSetHouseImgDtoRes setHouseImg(PostSetHouseImgDtoReq request, Long houseId) {
        Optional<House> result = houseRepository.findById(houseId);
        if (result.isPresent()) {
            House house = result.get();

            if (request.getImg() != null) {
                String savePath = ImageUtils.makeHouseImagePath(request.getImg().getOriginalFilename());
                savePath = s3Service.uploadFile(request.getImg(), savePath);
                house.setImg(savePath);
            } else {
                throw new HouseException(ErrorCode.IMAGE_EMPTY);
            }
            house = houseRepository.save(house);
            return PostSetHouseImgDtoRes.toDto(house);
        }
        return null;
    }

    public void deleteHouse(Long houseId) {
        houseRepository.delete(House.builder().id(houseId).build());
    }
}
