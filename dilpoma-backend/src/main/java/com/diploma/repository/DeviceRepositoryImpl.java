package com.diploma.repository;

import com.diploma.model.Device;
import com.diploma.model.DevicePredefinedValues;
import com.diploma.repository.interfaces.DeviceRepository;
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

    @Value("${sql.device.deleteDevice}")
    private String deleteDeviceRequest;

    @Value("${sql.device.createDevice}")
    private String createDeviceRequest;

    @Value("${sql.device.updateDevice}")
    private String updateDeviceRequest;

    @Value("${sql.device.getOSes}")
    private String osPredefinedValuesRequest;

    @Value("${sql.device.getFaceRecognitionTypes}")
    private String faceRecognitionPredefinedValuesRequest;

    @Value("${sql.device.getFingeprintSensorTypes}")
    private String fingerprintSensorPredefinedValuesRequest;


    public DeviceRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create(Device device) {
        if (device.getFaceRecognition().isEmpty()) {
            device.setFaceRecognition(null);
        }
        if (device.getFingerprintScanner().isEmpty()) {
            device.setFingerprintScanner(null);
        }
        return jdbcTemplate.queryForObject(createDeviceRequest, Long.class, device.getName(), device.getOs(), device.getOsMinVersion(),
                device.getOsMaxVersion(), device.getChipset(), device.getFingerprintScanner(), device.getFaceRecognition());
    }

    @Override
    public boolean update(Device device) {
        return this.jdbcTemplate.update(updateDeviceRequest, device.getName(), device.getOs(), device.getOsMinVersion(),
                device.getOsMaxVersion(), device.getChipset(), device.getFingerprintScanner(), device.getFaceRecognition(),
                device.getId()) != 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.jdbcTemplate.update(deleteDeviceRequest, id) != 0;
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
                request, new BeanPropertyRowMapper<>(Device.class), name, limit, offset
        );
    }

    @Override
    public int countFilteredDevices(String name) {
        return jdbcTemplate.queryForObject(countFilteredDevicesRequest, Integer.class, name);
    }

    @Override
    public DevicePredefinedValues getDevicePredefinedValues() {
        Collection<String> osCollection = jdbcTemplate.queryForList(
                osPredefinedValuesRequest, String.class);
        Collection<String> fingerprintSensorCollection = jdbcTemplate.queryForList(
                fingerprintSensorPredefinedValuesRequest, String.class);
        Collection<String> faceRecognitionCollection = jdbcTemplate.queryForList(
                faceRecognitionPredefinedValuesRequest, String.class);
        return new DevicePredefinedValues(osCollection, fingerprintSensorCollection, faceRecognitionCollection);
    }
}
