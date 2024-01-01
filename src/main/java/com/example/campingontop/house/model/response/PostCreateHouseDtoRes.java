package com.example.campingontop.house.model.response;

import com.example.campingontop.house.model.House;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateHouseDtoRes {
    private Long id;

    // private Integer user_id;

    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    private Integer maxUser;

    private Boolean hasAirConditioner;
    private Boolean hasWashingMachine;

    private Date createdAt;

    public static PostCreateHouseDtoRes toDto(House house) {
        return PostCreateHouseDtoRes.builder()
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
                .createdAt(house.getCreatedAt())
                .build();
    }
}
