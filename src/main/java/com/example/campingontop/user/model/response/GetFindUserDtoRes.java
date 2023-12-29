package com.example.campingontop.user.model.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFindUserDto {
    private Long id;
    private String email;
    private String name;
    private String nickName;
    private String phoneNum;
    private Boolean gender;
    private String birthDay;
    private Date createdAt;
    private Date updatedAt;
}
