package com.diploma.service.interfaces;

import com.diploma.dto.DeviceDTO;
import com.diploma.dto.PaginationDTO;
import com.diploma.dto.DevicePredefinedValuesDTO;

public interface DeviceService {

    PaginationDTO<DeviceDTO> getFilteredDevices(String name, int limit, boolean order, int currentPage);

    DeviceDTO getDeviceById(Long id);

    DeviceDTO createDevice(DeviceDTO deviceDTO);

    DeviceDTO updateDevice(DeviceDTO deviceDTO, long id);

    void deleteDevice(long id);

    DevicePredefinedValuesDTO getDevicePredefinedValues();

}