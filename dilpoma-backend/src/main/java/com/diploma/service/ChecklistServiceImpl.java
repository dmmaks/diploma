package com.diploma.service;

import com.diploma.dto.ChecklistDTO;
import com.diploma.dto.ChecklistEntryDTO;
import com.diploma.exception.CustomException;
import com.diploma.mapper.ChecklistMapper;
import com.diploma.model.Checklist;
import com.diploma.model.ChecklistEntry;
import com.diploma.model.Credentials;
import com.diploma.repository.interfaces.CredentialsRepository;
import com.diploma.repository.interfaces.ChecklistRepository;
import com.diploma.service.interfaces.ChecklistService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
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
            Collection<ChecklistEntryDTO> entryDTOs = checklistMapper.checklistEntryCollectionToDTOCollection(entries);
            ChecklistDTO checklistDTO = checklistMapper.checklistToChecklistDTO(checklist);
            checklistDTO.setEntries(entryDTOs);
            return checklistDTO;
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Checklist with id %s not found.", checklistId));
        }
    }

    @Override
    @Transactional
    public void updateIsChecked(String email, long checklistEntryId, boolean isChecked) {
        Credentials account = credentialsRepository.findByEmail(email);
        boolean updated = checklistRepository.updateIsChecked(checklistEntryId, isChecked, account.getId());
        if (!updated) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Checklist entry with id %s not found.", checklistEntryId));
        }
    }

    @Override
    public Collection<ChecklistDTO> getUserChecklists(String email, String searchRequest) {
        searchRequest = "%" + searchRequest + "%";
        Credentials account = credentialsRepository.findByEmail(email);
        Collection<Checklist> checklists = checklistRepository.findUserChecklists(searchRequest, account.getId());
        Collection<ChecklistDTO> checklistDTOs = checklistMapper.checklistCollectionToDTOCollection(checklists);
        return checklistDTOs;
    }

    @Override
    @Transactional
    public void updateChecklist(String email, long checklistId, String checklistName) {
        Credentials account = credentialsRepository.findByEmail(email);
        boolean updated = checklistRepository.updateChecklist(checklistName, checklistId, account.getId());
        if (!updated) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Checklist with id %s not found.", checklistId));
        }
    }

    @Override
    @Transactional
    public void deleteChecklistById(String email, long checklistId) {
        try {
            Credentials account = credentialsRepository.findByEmail(email);
            checklistRepository.deleteById(checklistId, account.getId());
        } catch (DataAccessException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Checklist with id %s not found.", checklistId));
        }
    }

}