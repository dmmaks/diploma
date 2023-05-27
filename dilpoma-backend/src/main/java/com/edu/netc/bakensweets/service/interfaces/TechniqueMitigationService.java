package com.edu.netc.bakensweets.service.interfaces;

import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;

public interface TechniqueMitigationService {
    TechniqueMitigationDTO getTechniqueMitigationById(Long id, TechniqueMitigationEntity entity);
}
