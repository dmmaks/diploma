package com.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePredefinedValues {
    private Collection<String> operatingSystems;
    private Collection<String> fingerprintSensorTypes;
    private Collection<String> faceRecognitionTypes;
}