package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.ChecklistDTO;
import com.edu.netc.bakensweets.model.ChecklistEntryDTO;
import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Mapper(componentModel = "spring")
public interface ChecklistMapper {
    @Mappings({
            @Mapping(target="id", source="checklistEntries.id"),
            @Mapping(target="checklistId", source="checklistEntries.checklistId"),
            @Mapping(target="techniqueId", source="checklistEntries.techniqueId"),
            @Mapping(target="techniqueName", source="checklistEntries.techniqueName"),
            @Mapping(target="techniqueDescription", source="checklistEntries.techniqueDescription"),
            @Mapping(target="mitigationId", source="checklistEntries.mitigationId"),
            @Mapping(target="mitigationName", source="checklistEntries.mitigationName"),
            @Mapping(target="mitigationDescription", source="checklistEntries.mitigationDescription"),
            @Mapping(target="isChecked", source="checklistEntries.isChecked")
    })
    Collection<ChecklistEntryDTO> checklistEntryCollectionToDTOCollection(Collection<ChecklistEntry> checklistEntries);

    @Mappings({
            @Mapping(target="id", source="checklist.id"),
            @Mapping(target="name", source="checklist.name"),
            @Mapping(target = "deviceName", source = "checklist.deviceName")
    })
    ChecklistDTO checklistToChecklistDTO(Checklist checklist);

    @Mappings({
            @Mapping(target="id", source="checklists.id"),
            @Mapping(target="name", source="checklists.name"),
            @Mapping(target = "deviceName", source = "checklists.deviceName")
    })
    Collection<ChecklistDTO> checklistCollectionToDTOCollection(Collection<Checklist> checklists);
}
