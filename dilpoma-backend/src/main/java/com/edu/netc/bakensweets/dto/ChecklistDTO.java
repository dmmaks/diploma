package com.edu.netc.bakensweets.dto;

import com.edu.netc.bakensweets.model.CheckListEntryDTO;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

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
    private Collection<CheckListEntryDTO> entries;
}
