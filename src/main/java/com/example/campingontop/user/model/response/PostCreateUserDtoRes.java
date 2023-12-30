package com.example.campingontop.user.model.response;

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
    private Boolean gender;
    private String birthDay;
    private Date createdAt;
}
