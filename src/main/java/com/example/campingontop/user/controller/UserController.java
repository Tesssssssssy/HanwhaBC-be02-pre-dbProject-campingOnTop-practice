package com.example.campingontop.user.controller;

import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.user.model.request.PostCreateUserDtoReq;
import com.example.campingontop.user.model.request.PutUpdateUserDtoReq;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
import com.example.campingontop.user.model.response.GetUserWithHouseLikeDtoRes;
import com.example.campingontop.user.model.response.PostCreateUserDtoRes;
import com.example.campingontop.user.model.response.PutUpdateUserDtoRes;
import com.example.campingontop.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity createUser(@Valid @RequestBody PostCreateUserDtoReq postCreateUserDtoReq) {
        PostCreateUserDtoRes response = userService.createUser(postCreateUserDtoReq);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "User 유저 조회",
            description = "유저 ID로 유저 1명을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/{userId}")
    public ResponseEntity findUserById(@Valid @Parameter(description = "조회할 user의 id") @PathVariable Long userId) {
        log.debug("[user] userId: {}", userId);
        GetFindUserDtoRes response = userService.findUserById(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "User 유저 목록 조회",
            description = "전체 유저들의 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/findList")
    public ResponseEntity findUserList() {
        List<GetFindUserDtoRes> userList = userService.findUserList();
        return ResponseEntity.ok().body(userList);
    }

    @Operation(summary = "User 유저 정보 수정",
            description = "유저의 정보를 수정하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@Valid @RequestBody PutUpdateUserDtoReq putUpdateUserDtoReq, @PathVariable Long userId) {
        PutUpdateUserDtoRes user = userService.updateUser(putUpdateUserDtoReq, userId);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "User 유저 삭제",
            description = "유저 ID로 유저 데이터 1개를 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@Valid @Parameter(description = "삭제할 user의 id") @PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User delete success");
    }


}
