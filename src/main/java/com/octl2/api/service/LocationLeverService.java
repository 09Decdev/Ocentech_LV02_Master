package com.octl2.api.service;

import org.springframework.stereotype.Service;

@Service
public interface LocationLeverService {
    void updateDefaultDelivery(Long locationId, Long ffmId, Long lmId, Long whId);
}