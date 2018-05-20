package io.muic.cs.ooc.lazyliving.backend.Repository;


import io.muic.cs.ooc.lazyliving.backend.entity.Device;
import io.muic.cs.ooc.lazyliving.backend.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void whenFindByIdAndFindByType(){
        //given
        Device device1 = new Device("Fanonanin");
        entityManager.persist(device1);
        entityManager.flush();

        Device device2 = new Device("Fanonanin");
        entityManager.persist(device2);
        entityManager.flush();
//        deviceRepository.save(device1);
//        deviceRepository.save(device2);

        //when
        List<Device> devicesByTypeFan = deviceRepository.findByDeviceType("Fanonanin");
        Device fan1 = deviceRepository.findByDeviceId(device1.getDeviceId());
//        Device device3 = new Device("bb");

        //then
        assertThat(devicesByTypeFan.size()).isEqualTo(2);
        assertThat(fan1 != null);

//        deviceRepository.save(device3);


//        List<Device> devicesByTypeAirCon = deviceRepository.findByDeviceType("bb");
//        assertThat(devicesByTypeAirCon.size()).isEqualTo(1);

    }

}
