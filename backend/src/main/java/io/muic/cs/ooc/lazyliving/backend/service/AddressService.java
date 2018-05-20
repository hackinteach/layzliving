package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.entity.Address;
import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.repository.AddressRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;
import io.muic.cs.ooc.lazyliving.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address){
        return addressRepository.save(address);
    }
}
