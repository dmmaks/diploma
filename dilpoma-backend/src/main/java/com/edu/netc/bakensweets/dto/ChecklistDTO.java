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
public class ChecklistDTO {
    @Pattern(regexp = "[0-9]+", message = "id should be numeric")
    private long id;
    @NotNull(message = "description is mandatory")
    @NotBlank(message = "description is mandatory")
    private String name;
    @Pattern(regexp = "[0-9]+", message = "id should be numeric")
    private long deviceId;
    @Pattern(regexp = "[0-9]+", message = "id should be numeric")
    private int accountId;
}
