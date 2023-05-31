package com.diploma.repository.interfaces;

import com.diploma.model.GeneratedModelEntry;
import com.diploma.model.ChecklistEntry;
import com.diploma.model.Device;

import java.util.Collection;


public interface ModelGenerationRepository {
    Collection<GeneratedModelEntry> getGeneratedModel(Device device);
    long createChecklist(String name, long deviceId, long accountId);

    void createChecklistEntries(Collection<ChecklistEntry> entries);
}
