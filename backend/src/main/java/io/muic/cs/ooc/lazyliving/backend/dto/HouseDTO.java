package io.muic.cs.ooc.lazyliving.backend.dto;

import io.muic.cs.ooc.lazyliving.backend.entity.Address;
import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import io.muic.cs.ooc.lazyliving.backend.entity.User;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

public class HouseDTO {
    private Long id;

    private String name;

    private Address address;

    private Set<User> owners = new HashSet<>();

    private Set<Room> rooms = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    //public HouseDTO(){}


}
