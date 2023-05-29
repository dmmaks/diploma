package com.edu.netc.bakensweets.controller;

import com.edu.netc.bakensweets.dto.DeviceDTO;
import com.edu.netc.bakensweets.dto.DevicePredefinedValuesDTO;
import com.edu.netc.bakensweets.dto.KitchenwareDTO;
import com.edu.netc.bakensweets.dto.PaginationDTO;
import com.edu.netc.bakensweets.service.interfaces.DeviceService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public PaginationDTO<DeviceDTO> getFilteredDevices(
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) @Min(value = 1, message = "Page size must be higher than 0") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) @Min(value = 0, message = "Current page must be higher than 0") int currentPage,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "order", defaultValue = "true", required = false) boolean order) {
        return deviceService.getFilteredDevices(name, pageSize, order, currentPage);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public DeviceDTO getDeviceById(@PathVariable long id) {
        return deviceService.getDeviceById(id);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device has been added"),
            @ApiResponse(code = 400, message = "Something went wrong")})
    public DeviceDTO createDevice(@RequestBody @Valid DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update successful"),
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Parameter(s) is/are invalid"),
            @ApiResponse(code = 404, message = "Item not found")})
    public DeviceDTO updateDevice(@PathVariable long id, @RequestBody @Valid DeviceDTO deviceDTO) {
        return deviceService.updateDevice(deviceDTO, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @DeleteMapping(value = "/{id}")
    public void deleteDevice(@PathVariable long id) {
        deviceService.deleteDevice(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping(value = "/predefinedValues")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public DevicePredefinedValuesDTO getDevicePredefinedValues() {
        return deviceService.getDevicePredefinedValues();
    }
}