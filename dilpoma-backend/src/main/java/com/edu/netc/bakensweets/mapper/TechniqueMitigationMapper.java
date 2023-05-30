package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.ApplicabilityDTO;
import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.dto.TechniqueMitigationWithLinksDTO;
import com.edu.netc.bakensweets.model.Applicability;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
            @Mapping(target="id", source="techniqueMitigation.id"),
            @Mapping(target="name", source="techniqueMitigation.name"),
            @Mapping(target="description", source="techniqueMitigation.description")
    })
    TechniqueMitigationWithLinksDTO techniqueMitigationToTechniqueMitigationWithLinksDTO(TechniqueMitigation techniqueMitigation);

    @Mappings({
            @Mapping(target="id", source="techniqueMitigationWithLinksDTO.id"),
            @Mapping(target="name", source="techniqueMitigationWithLinksDTO.name"),
            @Mapping(target="description", source="techniqueMitigationWithLinksDTO.description")
    })
    TechniqueMitigation techniqueMitigationWithLinksDTOToTechniqueMitigation(TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO);

    @Mappings({
            @Mapping(target="id", source="techniqueMitigations.id"),
            @Mapping(target="name", source="techniqueMitigations.name"),
            @Mapping(target="description", source="techniqueMitigations.description")
    })
    Collection<TechniqueMitigationDTO> techniqueMitigationCollectionToDtoCollection(Collection<TechniqueMitigation> techniqueMitigations);

    @Mappings({
            @Mapping(target="id", source="techniqueMitigationDTOs.id"),
            @Mapping(target="name", source="techniqueMitigationDTOs.name"),
            @Mapping(target="description", source="techniqueMitigationDTOs.description")
    })
    List<TechniqueMitigation> dtoCollectionTotechniqueMitigationCollection(Collection<TechniqueMitigationDTO> techniqueMitigationDTOs);

    @Mappings({
            @Mapping(target = "os", source = "applicability.os"),
            @Mapping(target = "osMinVersion", source = "applicability.osMinVersion"),
            @Mapping(target = "osMaxVersion", source = "applicability.osMaxVersion"),
            @Mapping(target = "chipset", source = "applicability.chipset"),
            @Mapping(target = "fingerprintScanner", source = "applicability.fingerprintScanner"),
            @Mapping(target = "faceRecognition", source = "applicability.faceRecognition")
    })
    ApplicabilityDTO applicablityToApplicabilityDTO(Applicability applicability);

    @Mappings({
            @Mapping(target = "os", source = "applicabilityDTO.os"),
            @Mapping(target = "osMinVersion", source = "applicabilityDTO.osMinVersion"),
            @Mapping(target = "osMaxVersion", source = "applicabilityDTO.osMaxVersion"),
            @Mapping(target = "chipset", source = "applicabilityDTO.chipset"),
            @Mapping(target = "fingerprintScanner", source = "applicabilityDTO.fingerprintScanner"),
            @Mapping(target = "faceRecognition", source = "applicabilityDTO.faceRecognition")
    })
    Applicability applicablityDTOToApplicability(ApplicabilityDTO applicabilityDTO);
}