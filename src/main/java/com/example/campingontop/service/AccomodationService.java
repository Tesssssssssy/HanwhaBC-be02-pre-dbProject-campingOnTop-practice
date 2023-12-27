package com.example.campingontop.service;

import com.example.campingontop.model.Accomodation;
import com.example.campingontop.model.dto.PostAccomodationDto;
import com.example.campingontop.repository.AccomodationRepository;
import org.springframework.stereotype.Service;

@Service
public class AccomodationService {
    private AccomodationRepository accomodationRepository;

    public AccomodationService(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }

    public void createAccomodation(PostAccomodationDto postAccomodationDto) {
        accomodationRepository.save(Accomodation.builder()
                        .name(postAccomodationDto.getName())
                        .address(postAccomodationDto.getAddress())
                        .latitude(postAccomodationDto.getLatitude())
                        .longitude(postAccomodationDto.getLongitude())
                        .content(postAccomodationDto.getContent())
                        .max_user(postAccomodationDto.getMax_user())
                        .price(postAccomodationDto.getPrice())
                        .img(postAccomodationDto.getImg())
                        .hasAirConditioner(postAccomodationDto.getHasAirConditioner())
                        .hasWashingMachine(postAccomodationDto.getHasWashingMachine())
                .build());
    }
}
