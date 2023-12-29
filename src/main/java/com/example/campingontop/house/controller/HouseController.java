package com.example.campingontop.accomodation.controller;

import com.example.campingontop.accomodation.PostAccomodationDto;
import com.example.campingontop.service.AccomodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name="Accomodation",description = "Accomodation 숙소 CRUD")
@Slf4j
@RestController
@RequestMapping("/api/v1/accomodation")
public class AccomodationController {
    private AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @Operation(summary = "Accomodation 숙소 등록",
            description = "숙소 등록를 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createAccomodation(@Valid PostAccomodationDto postAccomodationDto) {
        accomodationService.createAccomodation(postAccomodationDto);
        return ResponseEntity.ok().body("Accomodation 생성 성공");
    }


}
