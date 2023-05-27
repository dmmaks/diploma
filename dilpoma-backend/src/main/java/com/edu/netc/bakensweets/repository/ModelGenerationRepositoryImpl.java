package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.GeneratedModelEntry;
import com.edu.netc.bakensweets.repository.interfaces.ModelGenerationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ModelGenerationRepositoryImpl extends BaseJdbcRepository implements ModelGenerationRepository {

    @Value("${sql.modelGeneration.generate}")
    private String generateModelRequest;

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
                device.getOs() + " " + device.getOsMaxVersion(),
                device.getChipset(),
                device.getFingerprintScanner(),
                device.getFaceRecognition()
        );
    }
}