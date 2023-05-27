package com.edu.netc.bakensweets.repository;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.repository.interfaces.DeviceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DeviceRepositoryImpl extends BaseJdbcRepository implements DeviceRepository {

    @Value("${sql.device.filter}")
    private String filterRequest;

    @Value("${sql.device.findById}")
    private String findByIdRequest;

    @Value("${sql.device.countFiltered}")
    private String countFilteredDevicesRequest;

    public DeviceRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create(Device device) {
        return 0;
    }

    @Override
    public boolean update(Device device) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Device findById(Long id) {
        return jdbcTemplate.queryForObject(
                findByIdRequest, new BeanPropertyRowMapper<>(Device.class), id);
    }

    @Override
    public Collection<Device> filterDevices(String name, boolean order, int limit, int offset) {
        String request = String.format(filterRequest, order ? "ASC" : "DESC");
        return jdbcTemplate.query(
                request,
                new BeanPropertyRowMapper<>(Device.class), name, limit, offset
        );
    }

    @Override
    public int countFilteredDevices(String name) {
        return jdbcTemplate.queryForObject(countFilteredDevicesRequest, Integer.class, name);
    }
}
