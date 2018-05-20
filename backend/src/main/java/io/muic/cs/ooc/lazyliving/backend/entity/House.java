package io.muic.cs.ooc.lazyliving.backend.entity;

import io.muic.cs.ooc.lazyliving.backend.dto.HouseDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="house")
public class  House {

    @Id
    @GeneratedValue
    @Column(name="house_id")
    private long  houseId;

    @Column(name="name")
    private String name;

    @OneToOne
    private Address address;


    public House(){}

    @OneToMany
    private Set<Room> rooms = new HashSet<>();


//    public House(HouseDTO houseDTO){
//        this.houseId = houseDTO.getId();
//
//    }


    public void addRoom(Room room){
        rooms.add(room);
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
