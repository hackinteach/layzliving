package io.muic.cs.ooc.lazyliving.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.cs.ooc.lazyliving.backend.exception.DuplicateNameException;
import lombok.Data;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @Column(name="room_id")
    private Long roomId;

    private String name;

    @OneToOne
    @JsonIgnore
    private House house;

    @Column(name = "device")
    @OneToMany
    Set<Device> devices = new HashSet<>();
    public Room(){}



    public void setHouse(House house){
        this.house = house;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public House getHouse() {
        return house;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
