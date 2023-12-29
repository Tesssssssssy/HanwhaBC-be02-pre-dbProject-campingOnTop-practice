package com.example.campingontop.accomodation;

import com.example.campingontop.model.enums.hasAirConditioner;
import com.example.campingontop.model.enums.hasWashingMachine;
import com.example.campingontop.model.enums.isActive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchAccomodationDto {
    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    private Integer max_user;

    private isActive is_active;

    private hasAirConditioner has_airConditioner;
    private hasWashingMachine has_washingMachine;
}
