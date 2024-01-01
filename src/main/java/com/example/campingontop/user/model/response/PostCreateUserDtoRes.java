package com.example.campingontop.user.model.response;

import com.example.campingontop.enums.Gender;
import com.example.campingontop.user.model.User;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateUserDtoRes {
    private Long id;
    private String email;
    private String name;
    private String nickName;
    private String phoneNum;
    private Gender gender;
    private String birthDay;
    private Date createdAt;

    public static PostCreateUserDtoRes toDto(User user) {
        return PostCreateUserDtoRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phoneNum(user.getPhoneNum())
                .gender(user.getGender())
                .birthDay(user.getBirthDay())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
