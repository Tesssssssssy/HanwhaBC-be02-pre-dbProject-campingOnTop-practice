package com.example.campingontop.house.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HouseReadDtoRes {
    Long id;
    String name;
    Integer price;
}
