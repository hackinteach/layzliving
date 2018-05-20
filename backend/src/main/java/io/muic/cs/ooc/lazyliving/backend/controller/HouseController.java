package io.muic.cs.ooc.lazyliving.backend.controller;

import io.muic.cs.ooc.lazyliving.backend.dto.UserDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.*;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.HouseRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.RoomRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.UserRepository;
import io.muic.cs.ooc.lazyliving.backend.service.AddressService;
import io.muic.cs.ooc.lazyliving.backend.service.HouseService;
import io.muic.cs.ooc.lazyliving.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HouseController {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HouseService houseService;


    @Autowired
    private HouseRepository houseRepository;


    // add a new house for current login user
    @PostMapping(value="/add_house")
    public ResponseEntity<House> addHouse(@RequestParam(value = "houseName") String houseName,
                        @RequestParam("streetAddress") String streetAddress,
                        @RequestParam("city") String city,
                        @RequestParam("country") String country,
                        @RequestParam("zipCode") int zipCode, Authentication auth){
        User u =  (User)auth.getPrincipal();
        User user = userRepository.findByUsername(u.getUsername());
        House house = houseService.addHouse(houseName,streetAddress,city,country,zipCode,user);
        return ResponseEntity.ok(house);
    }

    // get a list of device object in a house from houseId
    @GetMapping(value = "/houses/all_devices")
    public List<Device> allDevicesInHouse(@RequestParam long houseId){
        return houseService.allDevicesInHouse(houseId);

    }

    @PostMapping(value="/house/remove")
    public ResponseEntity removeHouse(@RequestParam long houseId){
        House house = houseRepository.findById(houseId);
        houseRepository.delete(house);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // get energy consumed by house on date: dd, month: mm, year: yyyy
    @GetMapping(value="/house/energy")
    public long energyConsumedInHouseOnDate(@RequestParam long houseId, @RequestParam int dd,
                                           @RequestParam int mm, @RequestParam int yyyy){
        House house = houseService.findHouseById(houseId);
        return houseService.energyConsumedOnDate(dd,mm,yyyy,house);
    }


}
