package com.edu.netc.bakensweets.service.interfaces;

import com.edu.netc.bakensweets.dto.ChecklistDTO;

public interface ChecklistService {
    ChecklistDTO getChecklistById(String email, long checklistId);
}
