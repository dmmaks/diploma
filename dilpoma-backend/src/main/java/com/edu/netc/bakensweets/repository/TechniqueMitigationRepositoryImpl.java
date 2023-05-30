package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.Applicability;
import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.repository.interfaces.TechniqueMitigationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

    public TechniqueMitigationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    public TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity) {
        String request = String.format(findByIdRequest, entity.name());
        return jdbcTemplate.queryForObject(
                request, new BeanPropertyRowMapper<>(TechniqueMitigation.class), id);
    }

    public Applicability getApplicabilityByTechniqueId(Long id) {
        return jdbcTemplate.queryForObject(
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
}