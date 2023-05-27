package com.edu.netc.bakensweets.service;

import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.exception.CustomException;
import com.edu.netc.bakensweets.mapper.TechniqueMitigationMapper;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.repository.interfaces.TechniqueMitigationRepository;
import com.edu.netc.bakensweets.service.interfaces.TechniqueMitigationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TechniqueMitigationServiceImpl implements TechniqueMitigationService {
    private final TechniqueMitigationRepository techniqueMitigationRepository;
    private final TechniqueMitigationMapper techniqueMitigationMapper;

    @Override
    public TechniqueMitigationDTO getTechniqueMitigationById(Long id, TechniqueMitigationEntity entity) {
        try {
            return techniqueMitigationMapper.techniqueMitigationToTechniqueMitigationDTO(techniqueMitigationRepository.findByIdAndEntity(id, entity));
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("%s with id %s not found.", entity.toString(), id));
        }
    }

}