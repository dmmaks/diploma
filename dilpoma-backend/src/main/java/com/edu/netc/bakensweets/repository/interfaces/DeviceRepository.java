package com.edu.netc.bakensweets.repository.interfaces;

import com.edu.netc.bakensweets.model.Device;
import com.edu.netc.bakensweets.model.DevicePredefinedValues;

import java.util.Collection;

public interface DeviceRepository extends BaseCrudRepository<Device, Long> {
    Collection<Device> filterDevices(String name, boolean order, int limit, int offset);

    int countFilteredDevices(String name);

    DevicePredefinedValues getDevicePredefinedValues();
}