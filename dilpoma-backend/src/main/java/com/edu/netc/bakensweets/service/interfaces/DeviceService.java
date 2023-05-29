package com.edu.netc.bakensweets.service.interfaces;

import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.dto.DevicePredefinedValuesDTO;
import com.edu.netc.bakensweets.dto.PaginationDTO;

public interface DeviceService {

    PaginationDTO<DeviceDTO> getFilteredDevices(String name, int limit, boolean order, int currentPage);

    DeviceDTO getDeviceById(Long id);

    DeviceDTO createDevice(DeviceDTO deviceDTO);

    DeviceDTO updateDevice(DeviceDTO deviceDTO, long id);

    void deleteDevice(long id);

    DevicePredefinedValuesDTO getDevicePredefinedValues();

}