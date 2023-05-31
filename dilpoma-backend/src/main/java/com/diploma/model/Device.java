package com.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private long id;
    private String name;
    private String os;
    private String osMinVersion;
    private String osMaxVersion;
    private String chipset;
    private String fingerprintScanner;
    private String faceRecognition;
}
