package com.example.campingontop.house.model.response;

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

    private Integer max_user;

    private Boolean hasAirConditioner;
    private Boolean hasWashingMachine;

    private Date createdAt;
}
