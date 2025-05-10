package com.octl2.api.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MappingLevel {
    LEVEL_1(new String[]{
            Constants.PROVINCE_ID,
            Constants.PROVINCE_NAME,
            Constants.FFM_ID,
            Constants.FFM_NAME,
            Constants.LM_ID,
            Constants.LM_NAME,
            Constants.WH_ID,
            Constants.WH_NAME}),

    LEVEL_2(new String[]{
            Constants.PROVINCE_ID,
            Constants.PROVINCE_NAME,
            Constants.DISTRICT_ID,
            Constants.DISTRICT_NAME,
            Constants.FFM_ID,
            Constants.FFM_NAME,
            Constants.LM_ID,
            Constants.LM_NAME,
            Constants.WH_ID,
            Constants.WH_NAME}),

    LEVEL_3(new String[]{
            Constants.PROVINCE_ID,
            Constants.PROVINCE_NAME,
            Constants.DISTRICT_ID,
            Constants.DISTRICT_NAME,
            Constants.SUBDISTRICT_ID,
            Constants.SUBDISTRICT_NAME,
            Constants.FFM_ID,
            Constants.FFM_NAME,
            Constants.LM_ID,
            Constants.LM_NAME,
            Constants.WH_ID,
            Constants.WH_NAME});
    private final String[] headers;
}
