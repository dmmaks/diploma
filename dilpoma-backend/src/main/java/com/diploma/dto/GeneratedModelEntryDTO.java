package com.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedModelEntryDTO {
    @Pattern(regexp = "[0-9]+", message = "techniqueId should be numeric")
    private long techniqueId;
    @NotNull(message = "techniqueName is mandatory")
    @NotBlank(message = "techniqueName is mandatory")
    private String techniqueName;
    @NotNull(message = "techniqueDescription is mandatory")
    @NotBlank(message = "techniqueDescription is mandatory")
    private String techniqueDescription;
    @Pattern(regexp = "[0-9]+", message = "mitigationId should be numeric")
    private long mitigationId;
    @NotNull(message = "mitigationName is mandatory")
    @NotBlank(message = "mitigationName is mandatory")
    private String mitigationName;
    @NotNull(message = "mitigationDescription is mandatory")
    @NotBlank(message = "mitigationDescription is mandatory")
    private String mitigationDescription;
}
