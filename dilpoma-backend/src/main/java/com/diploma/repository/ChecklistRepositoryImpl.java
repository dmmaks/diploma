package com.diploma.repository;

import com.diploma.model.Checklist;
import com.diploma.model.ChecklistEntry;
import com.diploma.repository.interfaces.ChecklistRepository;
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

    @Value("${sql.checklist.updateIsChecked}")
    private String updateIsCheckedRequest;

    @Value("${sql.checklist.findUserChecklists}")
    private String findUserChecklistsRequest;

    @Value("${sql.checklist.deleteChecklist}")
    private String deleteChecklistRequest;

    @Value("${sql.checklist.updateChecklist}")
    private String updateChecklist;

    public ChecklistRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Checklist findChecklistById(long checklistId, long account_id) {
        return jdbcTemplate.queryForObject(
                findChecklistByIdRequest, new BeanPropertyRowMapper<>(Checklist.class), checklistId, account_id);
    }

    @Override
    public Collection<ChecklistEntry> findChecklistEntriesByChecklistId(long checklistId) {
        return jdbcTemplate.query(
                findEntriesByChecklistIdRequest, new BeanPropertyRowMapper<>(ChecklistEntry.class), checklistId);
    }

    @Override
    public boolean updateIsChecked(long checklistEntryId, boolean isChecked, long accountId) {
        return jdbcTemplate.update(updateIsCheckedRequest, isChecked, checklistEntryId, accountId) != 0;
    }

    @Override
    public boolean updateChecklist(String checklistName, long checklistEntryId, long accountId) {
        return jdbcTemplate.update(updateChecklist, checklistName, checklistEntryId, accountId) != 0;
    }

    @Override
    public boolean deleteById (long id, long account_id) {
        return this.jdbcTemplate.update(deleteChecklistRequest, id, account_id) != 0;
    }

    @Override
    public Collection<Checklist> findUserChecklists(String searchRequest, long accountId) {
        return jdbcTemplate.query(
                findUserChecklistsRequest, new BeanPropertyRowMapper<>(Checklist.class), accountId, searchRequest);
    }
}
