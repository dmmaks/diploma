package com.edu.netc.bakensweets.model;

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
    private long mitigationId;
    private boolean isChecked;
}
