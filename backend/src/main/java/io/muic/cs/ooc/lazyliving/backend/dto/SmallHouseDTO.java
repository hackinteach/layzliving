package io.muic.cs.ooc.lazyliving.backend.dto;

import io.muic.cs.ooc.lazyliving.backend.entity.House;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmallHouseDTO {
    private Long id;

    private String name;

    public SmallHouseDTO(House house){
        id = house.getHouseId();
        name = house.getName();
    }
}
