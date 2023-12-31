package com.example.campingontop.house.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutUpdateHouseDtoRes {
    private Long id;

    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    private Integer maxUser;

    private Boolean isActive;
    private Boolean hasAirConditioner;
    private Boolean hasWashingMachine;
}
