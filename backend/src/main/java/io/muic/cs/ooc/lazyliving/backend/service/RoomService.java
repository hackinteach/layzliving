package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import io.muic.cs.ooc.lazyliving.backend.exception.DuplicateNameException;
import io.muic.cs.ooc.lazyliving.backend.exception.EntityNotFoundException;
import io.muic.cs.ooc.lazyliving.backend.repository.HouseRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomService")
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private HouseRepository houseRepository;

    public Room getRoomById(long id){
        Room room = roomRepository.findById(id);
        if(room==null){
            throw new EntityNotFoundException();
        }
        return roomRepository.findById(id);
    }


    public Room addRoom(String roomName, House house){
        if(roomRepository.findByNameAndHouse(roomName,house)!=null){
            throw new DuplicateNameException();
        }
        Room room = new Room();
        room.setName(roomName);
        room.setHouse(house);
        room = roomRepository.save(room);
        house.addRoom(room);
        houseRepository.save(house);
        return room;

    }

    @Transactional
    public Room addRoom(List<String> devicesTypes, String roomName, House house) throws DuplicateNameException{
        //House house = houseRepository.findById(houseId);
        if(roomRepository.findByNameAndHouse(roomName,house)!=null){
            throw new DuplicateNameException();
        }
        Room room = new Room();
        room.setName(roomName);
        room.setHouse(house);
        //room = roomService.saveRoom(room);

        for(String deviceType : devicesTypes){
            Device device = new Device(deviceType);
            //device.setRoom(room);
            device = deviceService.saveDevice(device);
            room.addDevice(device);
            //room = roomService.saveRoom(room);
        }
        room = roomRepository.save(room);
        house.addRoom(room);
        houseRepository.save(house);
        return room;

    }
}
