package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.House;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface HouseRepository extends PagingAndSortingRepository<House,Long> {
    House findById(long houseId);
    //List<House> findByUserId(long userId);

}
