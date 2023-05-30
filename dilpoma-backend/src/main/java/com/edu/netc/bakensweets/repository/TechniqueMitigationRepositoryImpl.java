package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.Applicability;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.repository.interfaces.TechniqueMitigationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class TechniqueMitigationRepositoryImpl extends BaseJdbcRepository implements TechniqueMitigationRepository {

    @Value("${sql.techniqueMitigation.findById}")
    private String findByIdRequest;

    @Value("${sql.techniqueMitigation.filter}")
    private String filterRequest;

    @Value("${sql.techniqueMitigation.countFiltered}")
    private String countFilteredRequest;

    @Value("${sql.techniqueMitigation.findLinksByIdAndEntity}")
    private String findLinksByIdAndEntityRequest;

    @Value("${sql.techniqueMitigation.delete}")
    private String deleteRequest;

    @Value("${sql.techniqueMitigation.getApplicabilitiesForTechnique}")
    private String getApplicabilityRequest;

    @Value("${sql.techniqueMitigation.createTechniqueMitigation}")
    private String createTechniqueMitigationRequest;

    @Value("${sql.techniqueMitigation.createApplicability}")
    private String createApplicabilityRequest;

    @Value("${sql.techniqueMitigation.linkTechniqueApplicability}")
    private String linkTechniqueApplicabilityRequest;

    @Value("${sql.techniqueMitigation.linkTechniqueMitigation}")
    private String linkTechniqueMitigationRequest;

    @Value("${sql.techniqueMitigation.deleteApplicabilitiesByTechniqueId}")
    private String deleteApplicabilitiesByTechniqueId;

    @Value("${sql.techniqueMitigation.deleteLinksByTechniqueId}")
    private String deleteLinksByTechniqueId;

    @Value("${sql.techniqueMitigation.updateTechniqueMitigation}")
    private String updateTechniqueMitigation;

    public TechniqueMitigationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    public TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity) {
        String request = String.format(findByIdRequest, entity.name());
        return jdbcTemplate.queryForObject(
                request, new BeanPropertyRowMapper<>(TechniqueMitigation.class), id);
    }

    public List<Applicability> getApplicabilityByTechniqueId(Long id) {
        return jdbcTemplate.query(
                getApplicabilityRequest, new BeanPropertyRowMapper<>(Applicability.class), id, id, id, id, id, id);
    }

    @Override
    public Collection<TechniqueMitigation> findLinksByIdAndEntity(Long id, TechniqueMitigationEntity entity) {
        TechniqueMitigationEntity otherEntity;
        if (entity.equals(TechniqueMitigationEntity.TECHNIQUE)) {
            otherEntity = TechniqueMitigationEntity.MITIGATION;
        } else {
            otherEntity = TechniqueMitigationEntity.TECHNIQUE;
        }
        String request = String.format(findLinksByIdAndEntityRequest, otherEntity.name(), otherEntity.name(), otherEntity.name(), otherEntity.name(), entity.name());
        return jdbcTemplate.query(
                request, new BeanPropertyRowMapper<>(TechniqueMitigation.class), id);
    }

    @Override
    public Collection<TechniqueMitigation> filterTechniquesMitigations(String name, boolean order, int limit, int offset, TechniqueMitigationEntity entity) {
        String request = String.format(filterRequest, entity, order ? "ASC" : "DESC");
        return jdbcTemplate.query(
                request, new BeanPropertyRowMapper<>(TechniqueMitigation.class), name, limit, offset
        );
    }

    @Override
    public int countFilteredTechniquesMitigations(String name, TechniqueMitigationEntity entity) {
        String request = String.format(countFilteredRequest, entity);
        return jdbcTemplate.queryForObject(request, Integer.class, name);
    }

    @Override
    public boolean deleteByIdAndEntity(Long id, TechniqueMitigationEntity entity) {
        String request = String.format(deleteRequest, entity);
        return this.jdbcTemplate.update(request, id) != 0;
    }

    @Override
    public long createTechniqueMitigation(TechniqueMitigation techniqueMitigation, TechniqueMitigationEntity entity) {
        String request = String.format(createTechniqueMitigationRequest, entity);
        return jdbcTemplate.queryForObject(request, Long.class, techniqueMitigation.getName(), techniqueMitigation.getDescription());
    }

    @Override
    public void createTechniqueLinks(long techniqueId, List<TechniqueMitigation> mitigations) {
        this.jdbcTemplate.batchUpdate(linkTechniqueMitigationRequest, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, techniqueId);
                ps.setLong(2, mitigations.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return mitigations.size();
            }
        });
    }

    @Override
    @Transactional
    public void createApplicability(long techniqueId, Applicability applicability) {
        if (!applicability.getOs().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "os", applicability.getOs());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (!applicability.getOsMinVersion().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "os_min_version", applicability.getOsMinVersion());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (!applicability.getOsMaxVersion().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "os_max_version", applicability.getOsMaxVersion());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (!applicability.getChipset().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "chipset", applicability.getChipset());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (!applicability.getFingerprintScanner().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "fingerprint_scanner", applicability.getFingerprintScanner());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (!applicability.getFaceRecognition().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, "face_recognition", applicability.getFaceRecognition());
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
        if (applicability.getOs().isEmpty() && applicability.getOsMinVersion().isEmpty() && applicability.getOsMaxVersion().isEmpty()
        && applicability.getChipset().isEmpty() && applicability.getFingerprintScanner().isEmpty() && applicability.getFaceRecognition().isEmpty()) {
            long applicabilityId = jdbcTemplate.queryForObject(createApplicabilityRequest, Long.class, null, null);
            jdbcTemplate.update(linkTechniqueApplicabilityRequest, techniqueId, applicabilityId);
        }
    }


    @Override
    public boolean updateTechniqueMitigation(TechniqueMitigation techniqueMitigation, TechniqueMitigationEntity entity) {
        String request = String.format(updateTechniqueMitigation, entity);
        return this.jdbcTemplate.update(request, techniqueMitigation.getName(), techniqueMitigation.getDescription(), techniqueMitigation.getId()) != 0;
    }

    @Override
    public void deleteApplicabilitiesByTechniqueId(long id) {
        this.jdbcTemplate.update(deleteApplicabilitiesByTechniqueId, id);
    }

    @Override
    public void deleteLinksByTechniqueId(long id) {
        this.jdbcTemplate.update(deleteLinksByTechniqueId, id);
    }
}