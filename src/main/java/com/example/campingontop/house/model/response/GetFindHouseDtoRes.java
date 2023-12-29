package com.example.campingontop.accomodation.model.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFindAccomodationDto {
    private Integer id;

    // private Integer user_id;

    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    private Integer max_user;

    private Integer has_airConditioner;
    private Integer has_washingMachine;

    private Date register_time;
}
