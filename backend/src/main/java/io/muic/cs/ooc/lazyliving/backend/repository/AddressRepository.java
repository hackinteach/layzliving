package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address,Long> {
    List<Address> findAddressByAddressId(long addressId);
}
