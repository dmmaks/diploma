package com.diploma.service.interfaces;

import com.diploma.dto.GeneratedModelEntryDTO;

import java.util.Collection;

public interface ModelGenerationService {
    Collection<GeneratedModelEntryDTO> getGeneratedModel(String email, long deviceId);
}
