package com.example.campingontop.house.model.response;

import com.example.campingontop.house.model.House;
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

    public static PutUpdateHouseDtoRes toDto(House house) {
        return PutUpdateHouseDtoRes.builder()
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
    }
}
