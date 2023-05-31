package com.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicabilityDTO {
    private String os;
    private String osMinVersion;
    private String osMaxVersion;
    private String chipset;
    private String fingerprintScanner;
    private String faceRecognition;
}
