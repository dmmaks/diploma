package com.diploma.service;

import com.diploma.dto.GeneratedModelEntryDTO;
import com.diploma.exception.CustomException;
import com.diploma.mapper.GeneratedModelEntryMapper;
import com.diploma.model.GeneratedModelEntry;
import com.diploma.repository.interfaces.CredentialsRepository;
import com.diploma.repository.interfaces.ModelGenerationRepository;
import com.diploma.mapper.GeneratedModelToChecklistMapper;
import com.diploma.model.ChecklistEntry;
import com.diploma.model.Credentials;
import com.diploma.model.Device;
import com.diploma.repository.interfaces.DeviceRepository;
import com.diploma.service.interfaces.ModelGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class ModelGenerationServiceImpl implements ModelGenerationService {
    private final ModelGenerationRepository modelGenerationRepository;
    private final DeviceRepository deviceRepository;
    private final GeneratedModelEntryMapper generatedModelEntryMapper;
    private final GeneratedModelToChecklistMapper generatedModelToChecklistMapper;
    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public Collection<GeneratedModelEntryDTO> getGeneratedModel(String email, long deviceId) {
        try {
            Credentials account = credentialsRepository.findByEmail(email);
            Device device = deviceRepository.findById(deviceId);
            device.setChipset("%" + device.getChipset() + "%");
            Collection<GeneratedModelEntry> generatedModel = modelGenerationRepository.getGeneratedModel(device);
            long checklistId = modelGenerationRepository.createChecklist("Чек-лист для " + device.getName(), deviceId, account.getId());
            List<ChecklistEntry> checklistEntries = new ArrayList<>();
            for (GeneratedModelEntry entry : generatedModel) {
                ChecklistEntry checklistEntry = generatedModelToChecklistMapper.generatedModelEntryToChecklistEntry(entry);
                checklistEntry.setChecklistId(checklistId);
                checklistEntries.add(checklistEntry);
            }
            modelGenerationRepository.createChecklistEntries(checklistEntries);
            return generatedModelEntryMapper.generatedModelEntryPageToDtoCollection(generatedModel);
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Device with id %s not found.", deviceId));
        }
    }
}
