package com.example.campingontop.house.model.request;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateHouseDtoReq {
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
}
