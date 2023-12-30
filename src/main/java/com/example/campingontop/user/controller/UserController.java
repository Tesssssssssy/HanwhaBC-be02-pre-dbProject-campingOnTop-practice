package com.example.campingontop.user.controller;

import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.user.model.request.PostCreateUserDtoReq;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
import com.example.campingontop.user.model.response.PostCreateUserDtoRes;
import com.example.campingontop.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Tag(name="User", description = "User CRUD")
@Api(tags = "User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(HouseService.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "User 유저 회원가입",
            description = "유저의 회원가입을 하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PostMapping("/create")
    public ResponseEntity createUser(@Valid PostCreateUserDtoReq postCreateUserDtoReq) {
        PostCreateUserDtoRes response = userService.createUser(postCreateUserDtoReq);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "User 유저 조회",
            description = "유저 ID로 유저 1명을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/{userId}")
    public ResponseEntity findUserById(@Parameter(description = "조회할 user의 id") @PathVariable Long userId) {
        log.debug("[user] userId: {}", userId);
        GetFindUserDtoRes response = userService.findUserById(userId);
        return ResponseEntity.ok().body(response);
    }
}
