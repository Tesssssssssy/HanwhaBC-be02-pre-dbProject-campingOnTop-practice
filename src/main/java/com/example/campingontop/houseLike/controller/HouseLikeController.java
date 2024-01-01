package com.example.campingontop.houseLike.controller;

import com.example.campingontop.houseLike.model.dto.request.PostCreateHouseLikeDtoReq;
import com.example.campingontop.houseLike.service.HouseLikeService;
import com.example.campingontop.user.model.response.GetUserWithHouseLikeDtoRes;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="HouseLike", description = "HouseLike 숙소 좋아요 CRUD")
@Api(tags = "HouseLike")
@RestController
@RequestMapping("/api/v1/houseLike")
public class HouseLikeController {
    private HouseLikeService houseLikeService;

    public HouseLikeController(HouseLikeService houseLikeService) {
        this.houseLikeService = houseLikeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createHouseLike(@Valid @RequestBody PostCreateHouseLikeDtoReq request) {
        houseLikeService.createHouseLike(request);
        return ResponseEntity.ok().body("HouseLike create success");
    }

    @Operation(summary = "HouseLike 숙소 좋아요 + 유저 정보 조회",
            description = "유저 ID로 유저, 유저가 좋아요한 숙소 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/{userId}")
    public ResponseEntity<List<GetUserWithHouseLikeDtoRes>> findHouseLikeByUserId(@Valid @PathVariable Long userId) {
        List<GetUserWithHouseLikeDtoRes> likedHouses = houseLikeService.findHouseLikeByUserId(userId);
        return ResponseEntity.ok().body(likedHouses);
    }
}
