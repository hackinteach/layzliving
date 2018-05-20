package io.muic.cs.ooc.lazyliving.backend.controller;

import io.muic.cs.ooc.lazyliving.backend.dto.RoomDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import io.muic.cs.ooc.lazyliving.backend.exception.DuplicateNameException;
import io.muic.cs.ooc.lazyliving.backend.repository.RoomRepository;
import io.muic.cs.ooc.lazyliving.backend.service.HouseService;
import io.muic.cs.ooc.lazyliving.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseService houseService;

    @GetMapping("/room/list")
    public Set<RoomDTO> getRoomList(@RequestParam Long houseId){
        Set<RoomDTO> rdto = new HashSet<>();
        List<Room> rs = this.allRoomsByHouseId(houseId);
        rs.forEach(room ->
            rdto.add(new RoomDTO(room))
        );
        return rdto;
    }

    @PostMapping(path="/add_room")
    public ResponseEntity<Room> createRoom(
                                     @RequestParam String roomName,
                                     @RequestParam long houseId//,
    //                                 Authentication auth
                                     ) throws DuplicateNameException {

        System.out.println("name: "+roomName);
        System.out.println("id "+houseId);
        House house = houseService.findHouseById(houseId);
        Room room = roomService.addRoom(roomName,house);
        return ResponseEntity.ok(room);
    }

    @PostMapping(path="/room/remove")
    public ResponseEntity removeRoom(@RequestParam long roomId){
        Room room = roomRepository.findById(roomId);
        roomRepository.delete(room);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping(path="/room/devices")
    public Set<Device> devicesInRoom(@RequestParam long roomId){

        return roomService.getRoomById(roomId).getDevices();

    }

    @GetMapping(path="/all_rooms")
    public List<Room> allRoomsByHouseId(@RequestParam long houseId){
        return roomRepository.findAllByHouseHouseId(houseId);
    }
}
