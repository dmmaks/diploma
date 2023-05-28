package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.ChecklistDTO;
import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.model.CheckListEntryDTO;
import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import com.edu.netc.bakensweets.model.Device;
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
            @Mapping(target="mitigationId", source="checklistEntries.mitigationId"),
            @Mapping(target="isChecked", source="checklistEntries.isChecked")
    })
    Collection<CheckListEntryDTO> checklistEntryCollectionToDTOCollection(Collection<ChecklistEntry> checklistEntries);

    @Mappings({
            @Mapping(target="id", source="checklist.id"),
            @Mapping(target="name", source="checklist.name"),
            @Mapping(target = "deviceId", source = "checklist.deviceId")
    })
    ChecklistDTO checklistToChecklistDTO(Checklist checklist);
}
