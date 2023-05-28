package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.Checklist;
import com.edu.netc.bakensweets.model.ChecklistEntry;
import com.edu.netc.bakensweets.repository.interfaces.ChecklistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ChecklistRepositoryImpl extends BaseJdbcRepository implements ChecklistRepository {

    @Value("${sql.checklist.findChecklistById}")
    private String findChecklistByIdRequest;

    @Value("${sql.checklist.findEntriesByChecklistId}")
    private String findEntriesByChecklistIdRequest;

    public ChecklistRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Checklist findChecklistById(long id, long account_id) {
        return jdbcTemplate.queryForObject(
                findChecklistByIdRequest, new BeanPropertyRowMapper<>(Checklist.class), id, account_id);
    }

    @Override
    public Collection<ChecklistEntry> findChecklistEntriesByChecklistId(long id) {
        return jdbcTemplate.query(
                findEntriesByChecklistIdRequest, new BeanPropertyRowMapper<>(ChecklistEntry.class), id);
    }
}
