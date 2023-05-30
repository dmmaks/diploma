package com.edu.netc.bakensweets.service.interfaces;

import com.edu.netc.bakensweets.dto.ApplicabilityDTO;
import com.edu.netc.bakensweets.dto.PaginationDTO;
import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.dto.TechniqueMitigationWithLinksDTO;
import com.edu.netc.bakensweets.exception.CustomException;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
