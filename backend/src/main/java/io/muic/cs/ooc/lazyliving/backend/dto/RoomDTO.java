package io.muic.cs.ooc.lazyliving.backend.dto;

import io.muic.cs.ooc.lazyliving.backend.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {
    private long roomId;

    private String name;

    public RoomDTO(Room room){
        this.roomId = room.getRoomId();
        this.name = room.getName();
    }

}
