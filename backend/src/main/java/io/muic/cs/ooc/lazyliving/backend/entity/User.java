package io.muic.cs.ooc.lazyliving.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.cs.ooc.lazyliving.backend.dto.UserDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.House;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    public User(){

    }

    public User(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.userId = userDTO.getUserId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();


    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique=true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique=true)
    private String email;


    @ManyToMany //(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "join_user_house",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "house_id")
    )
    @JsonBackReference
    private Set<House> houses;

    public void addHouse(House house){
        houses.add(house);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
