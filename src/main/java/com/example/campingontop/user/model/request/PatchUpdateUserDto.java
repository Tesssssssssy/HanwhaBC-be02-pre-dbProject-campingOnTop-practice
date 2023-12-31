package com.example.campingontop.user.model.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PatchUpdateUserDto {
    @NotNull
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "특수문자 / 문자 / 숫자 포함 형태의 8~15자리 이내의 암호")
    @ApiParam(value = "비밀번호", required = true)
    private String password;

    @NotNull
    @Pattern(regexp = "[가-힣0-9]{2,5}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")
    @ApiParam(value = "성명", required = true, example = "campingOnTop")
    private String name;

    @NotNull
    @Pattern(regexp = "[가-힣0-9]{2,5}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")
    @ApiParam(value = "닉네임", required = true, example = "campingOnTop")
    private String nickName;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @ApiParam(value = "핸드폰 번호", required = true, example = "010-1111-2222")
    private String phoneNum;

    @ApiParam(value = "성별", required = true, example = "0: 남성 | 1: 여성")
    private Boolean gender;

    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")
    @ApiParam(value = "생년월일", example = "2023-12-29")
    private String birthDay;
}
