package com.octl2.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LmResponse {
    private Long lmId;
    private String lmName;
    private String lmShortname;
}
