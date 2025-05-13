package com.octl2.api.controller;

import com.octl2.api.dto.response.FfmDetailResponseDto;
import com.octl2.api.service.FfmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ffm-details")
@RequiredArgsConstructor
public class FfmDetailController {
    private final FfmService ffmService;

    @GetMapping("/all")
    public List<FfmDetailResponseDto> getAllFfmDetails() {
        return ffmService.getAllFfmDetails();
    }
}
