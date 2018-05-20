package io.muic.cs.ooc.lazyliving.backend.controller;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.mqtt.MqttService;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;
import io.muic.cs.ooc.lazyliving.backend.service.DeviceService;
import io.muic.cs.ooc.lazyliving.backend.service.EnergyRecordService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class DeviceController {

    private final MqttService mqttService = new MqttService();

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EnergyRecordService energyRecordService;

    public DeviceController() throws MqttException {
    }

    @PostMapping(value = "/device/add_device")
    public Device addDevice(@RequestParam String deviceName,
                            @RequestParam String deviceType,
                            @RequestParam long roomId){
        return deviceService.addDevice(deviceName,deviceType,roomId);
    }
    @PostMapping(value = "/device/remove")
    public ResponseEntity removeDevice(@RequestParam long deviceId){
        Device device = deviceRepository.findByDeviceId(deviceId);
        deviceRepository.delete(device);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value="/all_devices_types")
    public Set<String> allDevices(){
        return Device.getEnergyConsumedPerHour().keySet();
    }

    @PostMapping(value = "/device/on_off")
    public ResponseEntity turnOnOff(@RequestParam long deviceId){
        Device device = deviceRepository.findByDeviceId(deviceId);
        boolean isOn = device.isOn();
        try {
            String msg = (isOn)?deviceId+"-off": deviceId+"-on";
            mqttService.publish("1409",msg);
            deviceService.orderOnOff(device);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(device);

    }

    @GetMapping(value = "/device/energy_consumed")
    public double energyConsumedByDeviceOnDate(@RequestParam long deviceId, @RequestParam int dd,
                                             @RequestParam int mm, @RequestParam int yyyy){
        Device device = deviceRepository.findByDeviceId(deviceId);
        return energyRecordService.energyConsumedOnDate(dd,mm,yyyy,device);
    }

}
