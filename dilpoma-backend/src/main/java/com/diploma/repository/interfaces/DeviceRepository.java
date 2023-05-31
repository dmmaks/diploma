package com.diploma.repository.interfaces;

import com.diploma.model.Device;
import com.diploma.model.DevicePredefinedValues;

import java.util.Collection;

public interface DeviceRepository extends BaseCrudRepository<Device, Long> {
    Collection<Device> filterDevices(String name, boolean order, int limit, int offset);

    int countFilteredDevices(String name);

    DevicePredefinedValues getDevicePredefinedValues();
}