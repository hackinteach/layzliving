package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.entity.EnergyRecord;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.EnergyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service("energyRecordService")
public class EnergyRecordService {

    @Autowired
    EnergyRecordRepository energyRecordRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public double getEnergyConsumedPerHour(Device device){
        String deviceType =device.getDeviceType();
        System.out.println("deviceType: "+deviceType);
        Double energy = Device.getEnergyConsumedPerHour().get(deviceType);
        return (energy==null) ? 0:energy;
    }


    public void addEnergyOnDate(Device device, LocalDate date, double energy){
        EnergyRecord record = energyRecordRepository.findByDeviceAndDate(device,date);
        if(record != null){
            record.setEnergyConsumed(record.getEnergyConsumed()+energy);
        }else{
            record = new EnergyRecord();
            record.setDevice(device);
            record.setDate(date);
            record.setEnergyConsumed(energy);
        }
        energyRecordRepository.save(record);
    }

    public double energyConsumedOnDate(int dd, int mm, int yyyy, Device device){
        if(device.isOn()){
            updateEnergyUsed(device);
        }
        LocalDate date = LocalDate.of(yyyy,mm,dd);
        EnergyRecord record = energyRecordRepository.findByDeviceAndDate(device,date);
        if(record!=null){
            return record.getEnergyConsumed();
        }
        return 0;
    }

    public void updateEnergyUsed( Device device){

        double energyUsed = 0;
        double ePerHr = getEnergyConsumedPerHour(device);
        LocalDateTime lastUpdateDateTime = device.getLastUpdateDateTime();
        LocalDate lastUDate = lastUpdateDateTime.toLocalDate();
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowDate = LocalDate.now();
        device.setLastUpdateDateTime(nowDateTime);

        long years = lastUpdateDateTime.until( nowDateTime, ChronoUnit.YEARS);
        long months = lastUpdateDateTime.until( nowDateTime, ChronoUnit.MONTHS);
        long days = lastUpdateDateTime.until( nowDateTime, ChronoUnit.DAYS);
        long hours = lastUpdateDateTime.until(nowDateTime,ChronoUnit.HOURS);
        long minutes = lastUpdateDateTime.until(nowDateTime,ChronoUnit.MINUTES);

        System.out.println("years: "+years);
        System.out.println("months: "+months);
        System.out.println("days: "+days);
        System.out.println("hours: "+hours);
        System.out.println("minutes: "+minutes);

        if(lastUDate.equals(nowDate)){
            energyUsed += ePerHr * hours + ePerHr * (minutes/60.0);
            System.out.println("energy used: "+energyUsed);
            addEnergyOnDate(device,nowDate,energyUsed);
            return;
        }
        LocalDateTime firstDayDateTime = lastUDate.atTime(23,59);
        long hoursF = lastUpdateDateTime.until(firstDayDateTime,ChronoUnit.HOURS);
        long minutesF = lastUpdateDateTime.until(firstDayDateTime,ChronoUnit.MINUTES);
        double energyUsedF = ePerHr * hoursF + ePerHr * (minutesF/60.0);
        addEnergyOnDate(device,lastUDate,energyUsedF);

        LocalDate lastUpdateHereDate = lastUDate.plus(1,ChronoUnit.DAYS);

        while (lastUpdateHereDate.isBefore(nowDate)){
            addEnergyOnDate(device,lastUpdateHereDate,ePerHr*24);
            lastUpdateHereDate = lastUpdateHereDate.plus(1,ChronoUnit.DAYS);
        }

        LocalDateTime lastUpdateHereDateTime = lastUpdateHereDate.atTime(0,0);
        hours = lastUpdateHereDateTime.until(nowDateTime,ChronoUnit.HOURS);
        minutes = lastUpdateDateTime.until(nowDateTime,ChronoUnit.MINUTES);
        energyUsed = ePerHr * hours + ePerHr * (minutes/60.0);
        addEnergyOnDate(device,nowDate,energyUsed);

    }
}
