package com.diploma.service.interfaces;

import com.diploma.dto.ChecklistDTO;

import java.util.Collection;

public interface ChecklistService {
    ChecklistDTO getChecklistById(String email, long checklistId);

    void updateIsChecked(String email, long checklistEntryId, boolean isChecked);

    Collection<ChecklistDTO> getUserChecklists(String email, String searchRequest);

    void updateChecklist(String email, long checklistId, String checklistName);

    void deleteChecklistById(String email, long checklistId);
}
