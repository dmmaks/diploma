package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;

import java.util.Collection;

public interface TechniqueMitigationRepository {
    TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity);

    Collection<TechniqueMitigation> filterTechniquesMitigations(String name, boolean order, int limit, int offset, TechniqueMitigationEntity entity);

    int countFilteredTechniquesMitigations(String name, TechniqueMitigationEntity entity);

    Collection<TechniqueMitigation> findLinksByIdAndEntity(Long id, TechniqueMitigationEntity entity);
}