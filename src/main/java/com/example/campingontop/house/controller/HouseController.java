package com.example.campingontop.house.controller;

import com.example.campingontop.house.model.request.PostCreateHouseDtoReq;
import com.example.campingontop.house.model.request.PutUpdateHouseDtoReq;
import com.example.campingontop.house.model.response.GetFindHouseDtoRes;
import com.example.campingontop.house.model.response.PostCreateHouseDtoRes;
import com.example.campingontop.house.model.response.PutUpdateHouseDtoRes;
import com.example.campingontop.house.service.HouseService;
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

@Tag(name="House", description = "House 숙소 CRUD")
@Api(tags = "House")
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
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
    public ResponseEntity createHouse(@Valid @RequestBody PostCreateHouseDtoReq postCreateHouseDtoReq) {
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

    @Operation(summary = "House 숙소 목록 조회",
            description = "전체 숙소들의 목록을 조회하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @GetMapping("/findList")
    public ResponseEntity findHouseList() {
        List<GetFindHouseDtoRes> houseList = houseService.findHouseList();
        return ResponseEntity.ok().body(houseList);
    }

    @Operation(summary = "House 숙소 정보 수정",
            description = "숙소의 정보를 수정하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @PutMapping("/update/{houseId}")
    public ResponseEntity updateHouse(@Valid @RequestBody PutUpdateHouseDtoReq putUpdateHouseDtoReq, @PathVariable Long houseId) {
        PutUpdateHouseDtoRes house = houseService.updateHouse(putUpdateHouseDtoReq, houseId);
        return ResponseEntity.ok().body(house);
    }

    @Operation(summary = "House 숙소 삭제",
            description = "숙소 ID로 숙소 데이터 1개를 삭제하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity deleteHouse(@Parameter(description = "삭제할 house의 id") @PathVariable Long houseId) {
        houseService.deleteHouse(houseId);
        return ResponseEntity.ok().body("House delete success");
    }
}
