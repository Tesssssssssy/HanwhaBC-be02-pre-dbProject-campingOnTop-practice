package com.example.campingontop.user.model.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateUserDto {

    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @ApiParam(value = "이메일", required = true, example = "test01@naver.com")
    private String email;

    @NotNull
    @Min(8)
    @Max(15)
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
    @ApiParam(value = "비밀번호", required = true, example = "특수문자 / 문자 / 숫자 포함 형태의 8~15자리 이내의 암호")
    private String name;

    @Max(12)
    @ApiParam(value = "닉네임", required = true, example = "campingOnTop (최대 12자)")
    private String nickName;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @ApiParam(value = "핸드폰 번호", required = true, example = "010-1111-2222")
    private String phoneNum;

    @ApiParam(value = "성별", required = true, example = "0: 남성 | 1: 여성")
    private Integer gender;

    private LocalDateTime birthday;
}
