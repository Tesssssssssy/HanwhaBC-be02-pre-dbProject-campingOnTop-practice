package com.example.campingontop.house.controller;

import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PostCreateHouseDtoRes;
import com.example.campingontop.house.service.HouseService;
import com.example.campingontop.user.model.response.GetFindUserDtoRes;
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

@Tag(name="House", description = "House 숙소 CRUD")
@Api(tags = "House")
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final Logger log = LoggerFactory.getLogger(HouseService.class);
    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @Operation(summary = "House 숙소 등록",
            description = "숙소 등록를 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PostMapping("/create")
    public ResponseEntity createHouse(@Valid PostCreateHouseDtoReq postCreateHouseDtoReq) {
        PostCreateHouseDtoRes response = houseService.createHouse(postCreateHouseDtoReq);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "House 숙소 조회",
            description = "숙소 ID로 숙소 1개를 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/find/{houseId}")
    public ResponseEntity findHouseById(@Parameter(description = "조회할 house의 id") @PathVariable Long houseId) {
        log.debug("[house] houseId: {}", houseId);
        GetFindHouseDtoRes response = houseService.findHouseById(houseId);
        return ResponseEntity.ok().body(response);
    }
}
