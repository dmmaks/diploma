package com.diploma.service;

import com.diploma.dto.DeviceDTO;
import com.diploma.dto.PaginationDTO;
import com.diploma.exception.CustomException;
import com.diploma.mapper.DeviceMapper;
import com.diploma.model.Device;
import com.diploma.service.interfaces.DeviceService;
import com.diploma.dto.DevicePredefinedValuesDTO;
import com.diploma.repository.interfaces.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public PaginationDTO<DeviceDTO> getFilteredDevices(String name, int limit, boolean order, int currentPage) {
        name = "%" + name + "%";
        int count = deviceRepository.countFilteredDevices(name);
        Collection<Device> deviceCollection = deviceRepository.filterDevices(
                name, order, limit, currentPage * limit
        );
        return new PaginationDTO<>(
                deviceMapper.devicePageToDtoCollection(deviceCollection), count
        );
    }

    @Override
    public DeviceDTO getDeviceById(Long id) {
        try {
            return deviceMapper.deviceToDeviceDTO(deviceRepository.findById(id));
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Device with id %s not found.", id));
        }
    }

    @Override
    public void deleteDevice(long id) {
        try {
            deviceRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Device with id %s not found.", id));
        }
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        Device device = deviceMapper.deviceDTOToDevice(deviceDTO);
        long id = deviceRepository.create(device);
        deviceDTO.setId(id);
        return deviceDTO;
    }

    @Override
    public DeviceDTO updateDevice(DeviceDTO deviceDTO, long id) {
        deviceDTO.setId(id);
        Device device = deviceMapper.deviceDTOToDevice(deviceDTO);
        boolean updated = deviceRepository.update(device);
        if (!updated) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Device with id %s not found.", id));
        }
        return deviceDTO;
    }

    @Override
    public DevicePredefinedValuesDTO getDevicePredefinedValues() {
        return deviceMapper.devicePredefinedValuesToDTO(deviceRepository.getDevicePredefinedValues());
    }
}