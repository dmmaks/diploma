package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.repository.interfaces.TechniqueMitigationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TechniqueMitigationRepositoryImpl extends BaseJdbcRepository implements TechniqueMitigationRepository {

    @Value("${sql.techniqueMitigation.findById}")
    private String findByIdRequest;

    public TechniqueMitigationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create(TechniqueMitigation techniqueMitigation) {
        return 0;
    }

    @Override
    public boolean update(TechniqueMitigation techniqueMitigation) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public TechniqueMitigation findById(Long aLong) {
        throw new UnsupportedOperationException();
    }


    public TechniqueMitigation findByIdAndEntity(Long id, TechniqueMitigationEntity entity) {
        String request = String.format(findByIdRequest, entity.name());
        return jdbcTemplate.queryForObject(
                request, new BeanPropertyRowMapper<>(TechniqueMitigation.class), id);
    }
}