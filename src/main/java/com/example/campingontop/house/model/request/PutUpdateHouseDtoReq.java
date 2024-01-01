package com.example.campingontop.house.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutUpdateHouseDtoReq {
    private String name;
    private String content;
    private Integer price;

    private String address;
    private String latitude;
    private String longitude;

    private Integer maxUser;

    private Boolean isActive;
    private Boolean hasAirConditioner;
    private Boolean hasWashingMachine;
}
