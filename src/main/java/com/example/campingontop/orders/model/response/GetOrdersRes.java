package com.example.campingontop.orders.model.response;

import com.example.campingontop.house.model.response.HouseReadDtoRes;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetOrdersRes {
    Long id;
    String userName;
    List<HouseReadDtoRes> houses;
}
