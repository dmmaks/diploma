package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;

import java.util.Collection;

public interface TechniqueMitigationRepository extends BaseCrudRepository<TechniqueMitigation, Long> {
    TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity);
}