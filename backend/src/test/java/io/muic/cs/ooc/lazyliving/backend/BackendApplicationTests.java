package io.muic.cs.ooc.lazyliving.backend;

import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.service.HouseService;
import io.muic.cs.ooc.lazyliving.backend.entity.User;
import io.muic.cs.ooc.lazyliving.backend.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BackendApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private HouseService houseService;

	private User user1;
	private User user2;
	private House house2, house1;



	@Test
	public void userExist(){
//        System.out.println("SEED");
//        user1 = new User("May","lname","May","pwd","email");
//        user1 = userService.saveUser(user1);
//        house1 = new House();
//        house1.addOwner(user1);
//        house1 = houseService.saveHouse(house1);
//	    User test = userService.findByUsername("New");
//        System.out.println();
//		assertNotNull(test);
//
//		assertEquals(test.getEmail(), user1.getEmail());
//		assertEquals(test.getFirstName(), user1.getFirstName());
	}

	@Test
	public void addHouseToUser(){
//		assertEquals(true,house1.getUsers().contains(user1.getUserId()));
//		assertNotEquals(user1.getId(),house2.getOwner().getId());
	}

	@Test
	public void saveUserWithHouseDTO(){

	}

	@Test
	public void contextLoads() {

	}


//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		userRepository.save(new User("hackinteach","lname","uname",
//				"pwd","email"));
//	}
}
