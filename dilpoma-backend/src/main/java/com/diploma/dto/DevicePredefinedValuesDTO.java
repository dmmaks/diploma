package com.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePredefinedValuesDTO {
    private Collection<String> operatingSystems;
    private Collection<String> fingerprintSensorTypes;
    private Collection<String> faceRecognitionTypes;
}
