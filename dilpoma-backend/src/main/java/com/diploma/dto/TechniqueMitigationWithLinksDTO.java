package com.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechniqueMitigationWithLinksDTO {
    private long id;
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotNull(message = "description is mandatory")
    @NotBlank(message = "description is mandatory")
    private String description;

    Collection<TechniqueMitigationDTO> links;
}