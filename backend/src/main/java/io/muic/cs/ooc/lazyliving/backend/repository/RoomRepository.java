package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import io.muic.cs.ooc.lazyliving.backend.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    Room findById(long roomId);
    Room findByNameAndHouse(String roomName,House house);
    List<Room> findAllByHouseHouseId(long houseId);
    List<Room> findAllByHouse(House house);
   // Room
}
