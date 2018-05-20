package io.muic.cs.ooc.lazyliving.backend.dto;

import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

   //private String password;

    private Set<House> houses;

    private Set<Long> housesId = new HashSet<>();

    public UserDTO(User user){
        this.username = user.getUsername();
        this.userId = user.getUserId();
        System.out.println(userId);
        this.firstName = user.getFirstName();
        System.out.println(firstName);
        this.lastName = user.getLastName();
        System.out.println(lastName);
        this.email = user.getEmail();
        //this.password = user.getPassword();
        //System.out.println(email);
        if(user.getHouses()!=null){
            Set<House> houses = user.getHouses();
            System.out.println("house's si");
            System.out.println(houses.size());
            for(House h: houses){
                housesId.add(h.getHouseId());
            }
        }

        //this.housesId = user.
    }

//    public Set getHouseById(Long id){
//        return housesId.
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Long> getHousesId() {
        return housesId;
    }


}
