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
public class CheckListEntryDTO {
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
    @Pattern(regexp = "[0-9]+", message = "mitigationId should be numeric")
    @NotNull(message = "mitigationId is mandatory")
    @NotBlank(message = "mitigationId is mandatory")
    private long mitigationId;
    private boolean isChecked;
}
