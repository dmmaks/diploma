package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.model.ChecklistEntry;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface GeneratedModelToChecklistMapper {
    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntry.techniqueId"),
            @Mapping(target="mitigationId", source="generatedModelEntry.mitigationId")
    })
    ChecklistEntry generatedModelEntryToChecklistEntry(GeneratedModelEntry generatedModelEntry);
}
