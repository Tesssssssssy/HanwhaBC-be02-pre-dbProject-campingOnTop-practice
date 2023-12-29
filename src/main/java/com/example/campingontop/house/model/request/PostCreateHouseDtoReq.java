package com.example.campingontop.house.model.request;

import com.example.campingontop.enums.IsActive;
import lombok.*;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateHouseDto {
    private String name;
    private String content;
    private Integer price;
    private String img;

    private String address;
    private String latitude;
    private String longitude;

    @Max(10)
    private Integer max_user;


    private IsActive is_active;

    private String has_washingMachine;
    private String has_airConditioner;

    private LocalDateTime register_time;
}
