package com.example.campingontop.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMemberDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String nickName;
    private Long phoneNum;
    private Integer gender;
    private Integer is_active;
    private Date register_time;
}
