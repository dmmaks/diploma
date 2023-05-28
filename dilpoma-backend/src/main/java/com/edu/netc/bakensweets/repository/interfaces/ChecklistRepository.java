package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;

import java.util.Collection;

public interface ChecklistRepository {
    Collection<ChecklistEntry> findChecklistEntriesByChecklistId(long id);

    Checklist findChecklistById(long id, long account_id);

    boolean updateIsChecked(long checklistEntryId, boolean isChecked, long accountId);

    Collection<Checklist> findUserChecklists(String searchRequest, long accountId);

    boolean updateChecklist(String checklistName, long checklistEntryId, long accountId);

    boolean deleteById (long id, long account_id);
}