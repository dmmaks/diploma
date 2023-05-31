package com.diploma.service.interfaces;

import com.diploma.dto.PaginationDTO;
import com.diploma.dto.TechniqueMitigationDTO;
import com.diploma.dto.ApplicabilityDTO;
import com.diploma.dto.TechniqueMitigationWithLinksDTO;
import com.diploma.model.TechniqueMitigationEntity;

public interface TechniqueMitigationService {
    TechniqueMitigationDTO getTechniqueMitigationById(Long id, TechniqueMitigationEntity entity);

    PaginationDTO<TechniqueMitigationDTO> getFilteredTechniquesMitigations(String name, int limit, boolean order, int currentPage, TechniqueMitigationEntity entity);

    TechniqueMitigationWithLinksDTO getTechniqueMitigationWithLinksById(Long id, TechniqueMitigationEntity entity);

    void deleteTechniqueMitigation(Long id, TechniqueMitigationEntity entity);

    ApplicabilityDTO getApplicabilityByTechniqueId(Long id);

    void createTechnique(TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO, ApplicabilityDTO applicabilityDTO);

    void updateTechnique(long id, TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO, ApplicabilityDTO applicabilityDTO);

    void createMitigation(TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO);

    void updateMitigation(long id, TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO);
}
