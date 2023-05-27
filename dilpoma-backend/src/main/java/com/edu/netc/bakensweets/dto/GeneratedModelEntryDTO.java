package com.edu.netc.bakensweets.dto;

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
    @Pattern(regexp = "[0-9]+", message = "mitigationId should be numeric")
    private long mitigationId;
    @NotNull(message = "mitigationName is mandatory")
    @NotBlank(message = "mitigationName is mandatory")
    private String mitigationName;
}
