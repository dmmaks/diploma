package com.edu.netc.bakensweets.mapper;

import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Mapper(componentModel = "spring")
public interface DeviceMapper {
    @Mappings({
            @Mapping(target="id", source="deviceDTO.id"),
            @Mapping(target="name", source="deviceDTO.name"),
            @Mapping(target = "os", source = "deviceDTO.os"),
            @Mapping(target = "osMinVersion", source = "deviceDTO.osMinVersion"),
            @Mapping(target = "osMaxVersion", source = "deviceDTO.osMaxVersion"),
            @Mapping(target = "chipset", source = "deviceDTO.chipset"),
            @Mapping(target = "fingerprintScanner", source = "deviceDTO.fingerprintScanner"),
            @Mapping(target = "faceRecognition", source = "deviceDTO.faceRecognition")
    })
    Device deviceDTOToDevice(DeviceDTO deviceDTO);
    @Mappings({
            @Mapping(target="id", source="device.id"),
            @Mapping(target="name", source="device.name"),
            @Mapping(target = "os", source = "device.os"),
            @Mapping(target = "osMinVersion", source = "device.osMinVersion"),
            @Mapping(target = "osMaxVersion", source = "device.osMaxVersion"),
            @Mapping(target = "chipset", source = "device.chipset"),
            @Mapping(target = "fingerprintScanner", source = "device.fingerprintScanner"),
            @Mapping(target = "faceRecognition", source = "device.faceRecognition")
    })
    DeviceDTO deviceToDeviceDTO(Device device);

    @Mappings({
            @Mapping(target="id", source="devicePage.id"),
            @Mapping(target="name", source="devicePage.name"),
            @Mapping(target = "os", source = "devicePage.os"),
            @Mapping(target = "osMinVersion", source = "devicePage.osMinVersion"),
            @Mapping(target = "osMaxVersion", source = "devicePage.osMaxVersion"),
            @Mapping(target = "chipset", source = "devicePage.chipset"),
            @Mapping(target = "fingerprintScanner", source = "devicePage.fingerprintScanner"),
            @Mapping(target = "faceRecognition", source = "devicePage.faceRecognition")
    })
    Collection<DeviceDTO> devicePageToDtoCollection (Collection<Device> devicePage);
}