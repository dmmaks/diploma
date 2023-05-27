package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.GeneratedModelEntryDTO;
import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Mapper(componentModel = "spring")
public interface TechniqueMitigationMapper {
    @Mappings({
            @Mapping(target="id", source="techniqueMitigationDTO.id"),
            @Mapping(target="name", source="techniqueMitigationDTO.name"),
            @Mapping(target="description", source="techniqueMitigationDTO.description")
    })
    TechniqueMitigation techniqueMitigationDTOToGTechniqueMitigation(TechniqueMitigationDTO techniqueMitigationDTO);
    @Mappings({
            @Mapping(target="id", source="techniqueMitigation.id"),
            @Mapping(target="name", source="techniqueMitigation.name"),
            @Mapping(target="description", source="techniqueMitigation.description")
    })
    TechniqueMitigationDTO techniqueMitigationToTechniqueMitigationDTO(TechniqueMitigation techniqueMitigation);

    @Mappings({
            @Mapping(target="id", source="techniqueMitigations.id"),
            @Mapping(target="name", source="techniqueMitigations.name"),
            @Mapping(target="description", source="techniqueMitigations.description")
    })
    Collection<TechniqueMitigationDTO> techniqueMitigationPageToDtoCollection (Collection<TechniqueMitigation> techniqueMitigations);
}