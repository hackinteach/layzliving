package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.entity.EnergyRecord;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.time.LocalDate;

public interface EnergyRecordRepository extends PagingAndSortingRepository<EnergyRecord,Long> {

    EnergyRecord findByDeviceAndDate(Device device, LocalDate date);
    EnergyRecord findByDate(LocalDate date);
}
