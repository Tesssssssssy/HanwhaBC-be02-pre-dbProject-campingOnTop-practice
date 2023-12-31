package com.example.campingontop.user.model.response;

import com.example.campingontop.enums.Gender;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutUpdateUserDtoRes {
    private Long id;
    private String email;
    private String name;
    private String nickName;
    private String phoneNum;
    private Gender gender;
    private Date createdAt;
    private Date updatedAt;
}
