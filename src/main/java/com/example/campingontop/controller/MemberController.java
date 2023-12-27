package com.example.campingontop.controller;

import com.example.campingontop.model.dto.PostMemberDto;
import com.example.campingontop.service.MemberService;
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

@Tag(name="Member",description = "Member 유저 CRUD")
@Slf4j
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "Accomodation 숙소 등록",
            description = "숙소 등록를 등록하는 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "500",description = "서버 내부 오류")})
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createMember(@Valid PostMemberDto postMemberDto) {
        memberService.createMember(postMemberDto);
        return ResponseEntity.ok().body("Member 생성 성공");
    }
}
