package io.muic.cs.ooc.lazyliving.backend.entity;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name="device")
public class Device {
    @Id
    @GeneratedValue
    @Column(name="device_id")
    private Long deviceId;


    private static final Map<String, Double> energyConsumedPerHour;
    static
    {
        energyConsumedPerHour = new HashMap<>();
        energyConsumedPerHour.put("Fan", new Double(12));
        energyConsumedPerHour.put("LED", new Double(23));
        energyConsumedPerHour.put("Speaker", new Double(13));
    }

    private String deviceType;

    @Column(name="name")
    private String name;


    private LocalDateTime lastUpdateDateTime;



    private boolean isOn;

    private long stateValue;

    public Device(){

    }
    public Device(String deviceType){
        this.deviceType = deviceType;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    public static Map<String, Double> getEnergyConsumedPerHour() {
        return energyConsumedPerHour;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public long getStateValue() {
        return stateValue;
    }

    public void setStateValue(long stateValue) {
        this.stateValue = stateValue;
    }

}
