package com.example.campingontop.model.dto;

import lombok.*;

import javax.validation.constraints.Max;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAccomodationDto {
    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    @Max(10)
    private Integer max_user;

    private Integer hasAirConditioner;
    private Integer hasWashingMachine;
}
