package com.edu.netc.bakensweets.service;

import com.edu.netc.bakensweets.dto.GeneratedModelEntryDTO;
import com.edu.netc.bakensweets.exception.CustomException;
import com.edu.netc.bakensweets.mapper.GeneratedModelEntryMapper;
import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;
import com.edu.netc.bakensweets.repository.interfaces.DeviceRepository;
import com.edu.netc.bakensweets.repository.interfaces.ModelGenerationRepository;
import com.edu.netc.bakensweets.service.interfaces.ModelGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ModelGenerationServiceImpl implements ModelGenerationService {
    private final ModelGenerationRepository modelGenerationRepository;
    private final DeviceRepository deviceRepository;
    private final GeneratedModelEntryMapper generatedModelEntryMapper;

    @Override
    public Collection<GeneratedModelEntryDTO> getGeneratedModel(long deviceId) {
        try {
            Device device = deviceRepository.findById(deviceId);
            device.setChipset("%" + device.getChipset() + "%");
            Collection<GeneratedModelEntry> generatedModel = modelGenerationRepository.getGeneratedModel(device);
            return generatedModelEntryMapper.generatedModelEntryPageToDtoCollection(generatedModel);
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Device with id %s not found.", deviceId));
        }
    }
}
