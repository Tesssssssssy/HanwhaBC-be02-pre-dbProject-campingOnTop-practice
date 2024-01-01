package com.example.campingontop.user.model.response;

import com.example.campingontop.user.model.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSetUserImgDtoRes {
    private Long id;
    private String email;
    private String name;
    private String img;

    public static PostSetUserImgDtoRes toDto(User user) {
        return PostSetUserImgDtoRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .img(user.getImg())
                .build();
    }
}
