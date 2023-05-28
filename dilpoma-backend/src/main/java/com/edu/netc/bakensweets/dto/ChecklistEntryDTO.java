package com.edu.netc.bakensweets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistEntryDTO {
    @Pattern(regexp = "[0-9]+", message = "id should be numeric")
    private long id;
    @Pattern(regexp = "[0-9]+", message = "checklistId should be numeric")
    @NotNull(message = "checklistId is mandatory")
    @NotBlank(message = "checklistId is mandatory")
    private long checklistId;
    @Pattern(regexp = "[0-9]+", message = "techniqueId should be numeric")
    @NotNull(message = "techniqueId is mandatory")
    @NotBlank(message = "techniqueId is mandatory")
    private long techniqueId;
    @NotNull(message = "techniqueName is mandatory")
    @NotBlank(message = "techniqueName is mandatory")
    private String techniqueName;
    @NotNull(message = "techniqueDescription is mandatory")
    @NotBlank(message = "techniqueDescription is mandatory")
    private String techniqueDescription;
    @Pattern(regexp = "[0-9]+", message = "mitigationId should be numeric")
    @NotNull(message = "mitigationId is mandatory")
    @NotBlank(message = "mitigationId is mandatory")
    private long mitigationId;
    @NotNull(message = "mitigationName is mandatory")
    @NotBlank(message = "mitigationName is mandatory")
    private String mitigationName;
    @NotNull(message = "mitigationDescription is mandatory")
    @NotBlank(message = "mitigationDescription is mandatory")
    private String mitigationDescription;
    private boolean isChecked;
}
