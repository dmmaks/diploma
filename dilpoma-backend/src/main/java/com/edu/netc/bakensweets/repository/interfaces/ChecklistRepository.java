package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import com.edu.netc.bakensweets.model.Device;

import java.util.Collection;

public interface ChecklistRepository {
    Collection<ChecklistEntry> findChecklistEntriesByChecklistId(long id);

    Checklist findChecklistById(long id, long account_id);
}