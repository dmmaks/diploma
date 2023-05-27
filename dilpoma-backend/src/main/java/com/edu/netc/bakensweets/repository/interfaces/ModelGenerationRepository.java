package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;

import java.util.Collection;


public interface ModelGenerationRepository {
    Collection<GeneratedModelEntry> getGeneratedModel(Device device);
}
