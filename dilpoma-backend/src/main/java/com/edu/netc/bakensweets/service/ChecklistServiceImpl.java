package com.edu.netc.bakensweets.service;

import com.edu.netc.bakensweets.dto.ChecklistDTO;
import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.exception.CustomException;
import com.edu.netc.bakensweets.mapper.ChecklistMapper;
import com.edu.netc.bakensweets.model.CheckListEntryDTO;
import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import com.edu.netc.bakensweets.model.Credentials;
import com.edu.netc.bakensweets.repository.interfaces.ChecklistRepository;
import com.edu.netc.bakensweets.repository.interfaces.CredentialsRepository;
import com.edu.netc.bakensweets.service.interfaces.ChecklistService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ChecklistServiceImpl implements ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final ChecklistMapper checklistMapper;
    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public ChecklistDTO getChecklistById(String email, long checklistId) {
        try {
            Credentials account = credentialsRepository.findByEmail(email);
            Checklist checklist = checklistRepository.findChecklistById(checklistId, account.getId());
            Collection<ChecklistEntry> entries = checklistRepository.findChecklistEntriesByChecklistId(checklistId);
            Collection<CheckListEntryDTO> entryDTOs = checklistMapper.checklistEntryCollectionToDTOCollection(entries);
            ChecklistDTO checklistDTO = checklistMapper.checklistToChecklistDTO(checklist);
            checklistDTO.setEntries(entryDTOs);
            return checklistDTO;
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Checklist with id %s not found.", checklistId));
        }
    }

}