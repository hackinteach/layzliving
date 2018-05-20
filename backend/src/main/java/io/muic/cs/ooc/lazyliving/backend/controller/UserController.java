package io.muic.cs.ooc.lazyliving.backend.controller;

import io.muic.cs.ooc.lazyliving.backend.dto.SmallHouseDTO;
import io.muic.cs.ooc.lazyliving.backend.dto.UserDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.User;
import io.muic.cs.ooc.lazyliving.backend.service.UserService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    

    @Autowired
    private UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value="/register")
    public ResponseEntity register(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("repeatPassword") String repeatPass,
                                   @RequestParam("email") String email,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName){

//        Pair<User,String> result = userService.register(username,password,repeatPass,firstName,lastName,email);
        User user = userService.register(username,password,repeatPass,firstName,lastName,email);

        if(user==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkEmailIsOk")
    public boolean checkEmail(@RequestParam("email") String email){
        return userService.emailIsOk(email);
    }

    @PostMapping("/checkUsernameIsOk")
    public boolean checkUsername(@RequestParam("username") String username){
        return userService.usernameIsOk(username);
    }



    @GetMapping(value = "/user/houses")
    public Set<House> houseOfUserId(Authentication auth){

        User user = (User) auth.getPrincipal();
        System.out.println(user.getUserId());
        user = userService.findById(user.getUserId());

        return user.getHouses();

    }

    @GetMapping(value = "user/housesList")
    public Set<SmallHouseDTO> houseList(Authentication auth){

        User user = (User) auth.getPrincipal();
        System.out.println(user.getUserId());
        user = userService.findById(user.getUserId());
        Set<SmallHouseDTO> smdto = new HashSet<>();
        user.getHouses().forEach(house -> {
            smdto.add(new SmallHouseDTO(house));
        });
        return smdto;

    }

    @GetMapping(value="whoami")
    public User whoami(Authentication auth){
//        System.out.println(auth==null);
        User user = (User) auth.getPrincipal();
        System.out.println("user: "+user.getUsername());
//        return user.getUsername();
        return user;
    }


}
