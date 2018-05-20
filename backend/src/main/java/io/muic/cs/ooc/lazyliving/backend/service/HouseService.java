package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.entity.*;
import io.muic.cs.ooc.lazyliving.backend.exception.EntityNotFoundException;
import io.muic.cs.ooc.lazyliving.backend.repository.AddressRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.HouseRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.RoomRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("houseService")
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EnergyRecordService energyRecordService;


//
//    public HouseService(HouseRepository repo, UserService userService){
//        this.houseRepository = repo;
//        this.userService = userService;
//    }


    public House findHouseById(long id) throws EntityNotFoundException {
        House house = houseRepository.findById(id);
        if(house==null){
            throw new EntityNotFoundException();
        }
        return houseRepository.findById(id);
    }





    public House saveHouse(House house) {
        return houseRepository.save(house);
    }

    public House addHouse(String houseName, String streetAddress, String city, String country, int zipCode, User user){
        Address address = new Address();
        address.setStreetAddress(streetAddress);
        address.setCity(city);
        address.setCountry(country);
        address.setZipCode(zipCode);
        addressRepository.save(address);
        House house = new House();

        house.setName(houseName);
        house.setAddress(address);
        houseRepository.save(house);
        user.addHouse(house);
        userRepository.save(user);
        return house;
    }
    public List<Device> allDevicesInHouse(House house){
        List<Room> rooms = roomRepository.findAllByHouse(house);
        List<Device> devices = new ArrayList<>();
        for(Room room: rooms){
            devices.addAll(room.getDevices());
        }
        return devices;
    }

    public List<Device> allDevicesInHouse(long houseId){
        House house = houseRepository.findById(houseId);
        return allDevicesInHouse(house);
    }


    public long energyConsumedOnDate(int dd,int mm, int yyyy, House house){
        List<Device> devices = allDevicesInHouse(house);
        long totalEnergy = 0;
        for(Device device: devices){
            totalEnergy += energyRecordService.energyConsumedOnDate(dd,mm,yyyy,device);
        }

        return totalEnergy;
    }


}
