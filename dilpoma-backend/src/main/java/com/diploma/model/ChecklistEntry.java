package com.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistEntry {
    private long id;
    private long checklistId;
    private long techniqueId;
    private String techniqueName;
    private String techniqueDescription;
    private long mitigationId;
    private String mitigationName;
    private String mitigationDescription;
    private boolean isChecked;
}
