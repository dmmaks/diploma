package com.diploma.repository;

import com.diploma.model.GeneratedModelEntry;
import com.diploma.model.ChecklistEntry;
import com.diploma.model.Device;
import com.diploma.repository.interfaces.ModelGenerationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ModelGenerationRepositoryImpl extends BaseJdbcRepository implements ModelGenerationRepository {

    @Value("${sql.modelGeneration.generate}")
    private String generateModelRequest;

    @Value("${sql.modelGeneration.createChecklist}")
    private String createChecklistRequest;

    @Value("${sql.modelGeneration.createChecklistEntriesBeginning}")
    private String createChecklistEntriesBeginning;

    @Value("${sql.modelGeneration.createChecklistEntriesPart}")
    private String createChecklistEntriesPart;


    public ModelGenerationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Collection<GeneratedModelEntry> getGeneratedModel(Device device) {
        return jdbcTemplate.query(
                generateModelRequest,
                new BeanPropertyRowMapper<>(GeneratedModelEntry.class),
                device.getOs(),
                device.getOs() + " " + device.getOsMinVersion(),
                device.getOs(),
                device.getOs() + " " + device.getOsMaxVersion(),
                device.getOs(),
                device.getChipset(),
                device.getFingerprintScanner(),
                device.getFaceRecognition()
        );
    }

    @Override
    public long createChecklist(String name, long deviceId, long accountId) {
        return jdbcTemplate.queryForObject(createChecklistRequest, Long.class, name, deviceId, accountId);
    }

    @Override
    public void createChecklistEntries(Collection<ChecklistEntry> entries) {
        StringBuilder requestBuilder = new StringBuilder(createChecklistEntriesBeginning);
        List<Long> params = new ArrayList<>();
        for (ChecklistEntry entry : entries) {
            requestBuilder.append(createChecklistEntriesPart);
            params.add(entry.getChecklistId());
            params.add(entry.getTechniqueId());
            params.add(entry.getMitigationId());
        }
        requestBuilder.deleteCharAt(requestBuilder.length() - 1);
        jdbcTemplate.update(requestBuilder.toString(), params.toArray());
    }
}