package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.GeneratedModelEntryDTO;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Mapper(componentModel = "spring")
public interface GeneratedModelEntryMapper {
    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntryDTO.techniqueId"),
            @Mapping(target="techniqueName", source="generatedModelEntryDTO.techniqueName"),
            @Mapping(target = "mitigationId", source = "generatedModelEntryDTO.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntryDTO.mitigationName")
    })
    GeneratedModelEntry generatedModelEntryDTOToGeneratedModelEntry(GeneratedModelEntryDTO generatedModelEntryDTO);
    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntry.techniqueId"),
            @Mapping(target="techniqueName", source="generatedModelEntry.techniqueName"),
            @Mapping(target = "mitigationId", source = "generatedModelEntry.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntry.mitigationName")
    })
    GeneratedModelEntryDTO generatedModelEntryToGeneratedModelEntryDTO(GeneratedModelEntry generatedModelEntry);

    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntryPage.techniqueId"),
            @Mapping(target="techniqueName", source="generatedModelEntryPage.techniqueName"),
            @Mapping(target = "mitigationId", source = "generatedModelEntryPage.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntryPage.mitigationName")
    })
    Collection<GeneratedModelEntryDTO> generatedModelEntryPageToDtoCollection (Collection<GeneratedModelEntry> generatedModelEntryPage);
}