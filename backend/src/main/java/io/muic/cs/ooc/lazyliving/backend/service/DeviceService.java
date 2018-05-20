package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.entity.EnergyRecord;
import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import io.muic.cs.ooc.lazyliving.backend.repository.EnergyRecordRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;

import io.muic.cs.ooc.lazyliving.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service("deviceService")
public class DeviceService {

    @Autowired
    private EnergyRecordService energyRecordService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EnergyRecordRepository energyRecordRepository;


    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }


    public void orderOnOff(Device device){ // **** add exception
        //Device device = deviceRepository.findByDeviceId(deviceId);
         // if it is not our device type // add exception
        if(device.isOn()){
            device.setOn(false);
            //update energy consume
            // from lastOrderDateTime to now
            energyRecordService.updateEnergyUsed(device);
        }else{
            device.setOn(true);
        }
        device.setLastUpdateDateTime(LocalDateTime.now());
        deviceRepository.save(device); // need this ??
    }

    public Device addDevice(String deviceName, String deviceType, long roomId){
        Device device = new Device();
        device.setDeviceType(deviceType);
        device.setName(deviceName);
        deviceRepository.save(device);
        Room room = roomRepository.findById(roomId);
        room.getDevices().add(device);
        roomRepository.save(room);
        return device;
    }

    // update energy from lastUpdateTime to now in db


//
}
