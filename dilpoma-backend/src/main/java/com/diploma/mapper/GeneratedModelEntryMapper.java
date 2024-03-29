package com.diploma.mapper;

import com.diploma.dto.GeneratedModelEntryDTO;
import com.diploma.model.GeneratedModelEntry;
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
            @Mapping(target="techniqueDescription", source="generatedModelEntryDTO.techniqueDescription"),
            @Mapping(target = "mitigationId", source = "generatedModelEntryDTO.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntryDTO.mitigationName"),
            @Mapping(target = "mitigationDescription", source = "generatedModelEntryDTO.mitigationDescription")
    })
    GeneratedModelEntry generatedModelEntryDTOToGeneratedModelEntry(GeneratedModelEntryDTO generatedModelEntryDTO);
    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntry.techniqueId"),
            @Mapping(target="techniqueName", source="generatedModelEntry.techniqueName"),
            @Mapping(target="techniqueDescription", source="generatedModelEntry.techniqueDescription"),
            @Mapping(target = "mitigationId", source = "generatedModelEntry.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntry.mitigationName"),
            @Mapping(target = "mitigationDescription", source = "generatedModelEntry.mitigationDescription")
    })
    GeneratedModelEntryDTO generatedModelEntryToGeneratedModelEntryDTO(GeneratedModelEntry generatedModelEntry);

    @Mappings({
            @Mapping(target="techniqueId", source="generatedModelEntryPage.techniqueId"),
            @Mapping(target="techniqueName", source="generatedModelEntryPage.techniqueName"),
            @Mapping(target="techniqueDescription", source="generatedModelEntryPage.techniqueDescription"),
            @Mapping(target = "mitigationId", source = "generatedModelEntryPage.mitigationId"),
            @Mapping(target = "mitigationName", source = "generatedModelEntryPage.mitigationName"),
            @Mapping(target = "mitigationDescription", source = "generatedModelEntryPage.mitigationDescription")
    })
    Collection<GeneratedModelEntryDTO> generatedModelEntryPageToDtoCollection (Collection<GeneratedModelEntry> generatedModelEntryPage);
}