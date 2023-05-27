package com.edu.netc.bakensweets.service.interfaces;

import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.dto.GeneratedModelEntryDTO;

import java.util.Collection;

public interface ModelGenerationService {
    Collection<GeneratedModelEntryDTO> getGeneratedModel(long deviceId);
}
