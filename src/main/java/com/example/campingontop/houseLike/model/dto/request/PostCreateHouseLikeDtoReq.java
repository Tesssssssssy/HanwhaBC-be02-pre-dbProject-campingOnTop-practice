package com.example.campingontop.houseLike.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateHouseLikeDtoReq {
    private Long userId;
    private Long houseId;
}
