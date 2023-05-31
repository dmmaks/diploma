package com.diploma.mapper;

import com.diploma.model.ChecklistEntry;
import com.diploma.model.GeneratedModelEntry;
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
