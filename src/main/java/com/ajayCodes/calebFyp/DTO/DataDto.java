package com.ajayCodes.calebFyp.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataDto {
    private Long dataId;
    private String heartrate;
    private String oxygenLevel;
    private String longLat;
}
