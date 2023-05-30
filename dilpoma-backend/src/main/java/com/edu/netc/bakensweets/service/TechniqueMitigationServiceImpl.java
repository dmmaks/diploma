package com.edu.netc.bakensweets.service;

import com.edu.netc.bakensweets.dto.*;
import com.edu.netc.bakensweets.exception.CustomException;
import com.edu.netc.bakensweets.mapper.TechniqueMitigationMapper;
import com.edu.netc.bakensweets.model.Applicability;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.repository.interfaces.TechniqueMitigationRepository;
import com.edu.netc.bakensweets.service.interfaces.TechniqueMitigationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

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

    @Override
    public ApplicabilityDTO getApplicabilityByTechniqueId(Long id) {
        try {
            List<Applicability> applicabilityList = techniqueMitigationRepository.getApplicabilityByTechniqueId(id);
            if (applicabilityList.isEmpty()) {
                return new ApplicabilityDTO();
            }
            return techniqueMitigationMapper.applicablityToApplicabilityDTO(applicabilityList.get(0));
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Technique with id %s not found.", id));
        }
    }

    @Override
    public PaginationDTO<TechniqueMitigationDTO> getFilteredTechniquesMitigations(String name, int limit, boolean order, int currentPage, TechniqueMitigationEntity entity) {
        name = "%" + name + "%";
        int count = techniqueMitigationRepository.countFilteredTechniquesMitigations(name, entity);
        Collection<TechniqueMitigation> techniqueMitigationCollection = techniqueMitigationRepository.filterTechniquesMitigations(
                name, order, limit, currentPage * limit, entity
        );
        return new PaginationDTO<>(
                techniqueMitigationMapper.techniqueMitigationCollectionToDtoCollection(techniqueMitigationCollection), count
        );
    }

    @Override
    @Transactional
    public TechniqueMitigationWithLinksDTO getTechniqueMitigationWithLinksById(Long id, TechniqueMitigationEntity entity) {
        try {
            TechniqueMitigationWithLinksDTO mainDto =
                    techniqueMitigationMapper.techniqueMitigationToTechniqueMitigationWithLinksDTO(
                            techniqueMitigationRepository.findByIdAndEntity(id, entity));
            mainDto.setLinks(techniqueMitigationMapper.techniqueMitigationCollectionToDtoCollection(
                    techniqueMitigationRepository.findLinksByIdAndEntity(id, entity)));
            return mainDto;
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("%s with id %s not found.", entity.toString(), id));
        }
    }

    @Override
    public void deleteTechniqueMitigation(Long id, TechniqueMitigationEntity entity) {
        try {
            techniqueMitigationRepository.deleteByIdAndEntity(id, entity);
        } catch (DataAccessException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("%s with id %s not found.", entity, id));
        }
    }

    @Override
    @Transactional
    public void createTechnique(TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO, ApplicabilityDTO applicabilityDTO) {
        TechniqueMitigation technique = techniqueMitigationMapper.techniqueMitigationWithLinksDTOToTechniqueMitigation(techniqueMitigationWithLinksDTO);
        List<TechniqueMitigation> links = techniqueMitigationMapper.dtoCollectionTotechniqueMitigationCollection(techniqueMitigationWithLinksDTO.getLinks());
        Applicability applicability = techniqueMitigationMapper.applicablityDTOToApplicability(applicabilityDTO);
        long techniqueId = techniqueMitigationRepository.createTechniqueMitigation(technique, TechniqueMitigationEntity.TECHNIQUE);
        techniqueMitigationRepository.createApplicability(techniqueId, applicability);
        techniqueMitigationRepository.createTechniqueLinks(techniqueId, links);
    }

    @Override
    @Transactional
    public void updateTechnique(long id, TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO, ApplicabilityDTO applicabilityDTO) {
        try {
            techniqueMitigationRepository.deleteApplicabilitiesByTechniqueId(id);
            techniqueMitigationRepository.deleteLinksByTechniqueId(id);
            TechniqueMitigation technique = techniqueMitigationMapper.techniqueMitigationWithLinksDTOToTechniqueMitigation(techniqueMitigationWithLinksDTO);
            technique.setId(id);
            techniqueMitigationRepository.updateTechniqueMitigation(technique, TechniqueMitigationEntity.TECHNIQUE);
            List<TechniqueMitigation> links = techniqueMitigationMapper.dtoCollectionTotechniqueMitigationCollection(techniqueMitigationWithLinksDTO.getLinks());
            Applicability applicability = techniqueMitigationMapper.applicablityDTOToApplicability(applicabilityDTO);
            techniqueMitigationRepository.createApplicability(id, applicability);
            techniqueMitigationRepository.createTechniqueLinks(id, links);

        } catch (DataAccessException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Technique with id %s not found.", id));
        }
    }
}