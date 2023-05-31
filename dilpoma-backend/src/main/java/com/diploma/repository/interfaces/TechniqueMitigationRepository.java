package com.diploma.repository.interfaces;

import com.diploma.model.TechniqueMitigation;
import com.diploma.model.Applicability;
import com.diploma.model.TechniqueMitigationEntity;

import java.util.Collection;
import java.util.List;

public interface TechniqueMitigationRepository {
    TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity);

    Collection<TechniqueMitigation> filterTechniquesMitigations(String name, boolean order, int limit, int offset, TechniqueMitigationEntity entity);

    int countFilteredTechniquesMitigations(String name, TechniqueMitigationEntity entity);

    Collection<TechniqueMitigation> findLinksByIdAndEntity(Long id, TechniqueMitigationEntity entity);

    boolean deleteByIdAndEntity(Long id, TechniqueMitigationEntity entity);

    List<Applicability> getApplicabilityByTechniqueId(Long id);

    long createTechniqueMitigation(TechniqueMitigation technique, TechniqueMitigationEntity entity);

    void createTechniqueLinks(long techniqueId, List<TechniqueMitigation> mitigations);

    void createMitigationLinks(long mitigationId, List<TechniqueMitigation> techniques);

    void createApplicability(long techniqueId, Applicability applicability);

    boolean updateTechniqueMitigation(TechniqueMitigation techniqueMitigation, TechniqueMitigationEntity entity);

    void deleteApplicabilitiesByTechniqueId(long id);

    void deleteLinksByTechniqueMitigationId(long id, TechniqueMitigationEntity entity);

}