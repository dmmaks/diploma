package com.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicability {
    private String os;
    private String osMinVersion;
    private String osMaxVersion;
    private String chipset;
    private String fingerprintScanner;
    private String faceRecognition;
}
