package com.edu.netc.bakensweets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    @Id
    private long id;
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotNull(message = "os is mandatory")
    @NotBlank(message = "os is mandatory")
    private String os;
    @NotNull(message = "osMinVersion is mandatory")
    @NotBlank(message = "osMinVersion is mandatory")
    private String osMinVersion;
    @NotNull(message = "osMaxVersion is mandatory")
    @NotBlank(message = "osMaxVersion is mandatory")
    private String osMaxVersion;
    @NotNull(message = "chipset is mandatory")
    @NotBlank(message = "chipset is mandatory")
    private String chipset;
    private String fingerprintScanner;
    private String faceRecognition;
}
