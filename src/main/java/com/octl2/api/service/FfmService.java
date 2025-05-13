package com.octl2.api.service;

import com.octl2.api.dto.response.FfmDetailResponseDto;

import java.util.List;

public interface FfmService {
    FfmDetailResponseDto getFfmDetail(Long ffmId);
    List<FfmDetailResponseDto> getAllFfmDetails();
} 