package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeviceRepository extends PagingAndSortingRepository<Device,Long> {

    List<Device> findByDeviceType(String type);

    Device findByDeviceId(long deviceId);



}
