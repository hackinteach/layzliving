package io.muic.cs.ooc.lazyliving.backend.service;

import io.muic.cs.ooc.lazyliving.backend.dto.UserDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.House;
import io.muic.cs.ooc.lazyliving.backend.entity.User;
import io.muic.cs.ooc.lazyliving.backend.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
//        this.passwordEncoder = encoder;
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){return userRepository.findAll();}

    public User findByUsername(String username) {return userRepository.findByUsername(username);}

    public void addHouse(House house, User user){
        if ( user.getHouses() == null){
            return;
//            throw new // some exception
        }

        user.getHouses().add(house);
    }

    public Set<House> listUserHouse(User user){
        return user.getHouses();
    }

    public User authenticate(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }

        User user = userRepository.findByUsername(username);

        if(user == null){
            return null;
        }
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }

    public User register(String username, String password,
                                         String repeatPass, String firstName,
                                         String lastName, String email){
        String error = "";
        User user = null;
        System.out.println("registering");

         if(username==null || password == null || repeatPass == null ||
                firstName == null|| lastName == null || email==null) {
            error= "You did not fill in all the information needed.";

        } else if(userRepository.findByUsername(username)!=null){
             error = "Username already exists.";
         }else if(userRepository.findByEmail(email)!= null){
            error = "This email is already registered.";
        }else if(!password.equals(repeatPass)){
            error = "Passwords do not match.";

        }else{
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEmail(email);
            user.setUsername(username);
            userRepository.save(user);
        }
        System.out.println("error: "+error);

        return user;
    }
    public boolean emailIsOk(String email){
        User user = userRepository.findByEmail(email);
        System.out.println("emailIsOk == "+(user==null));
        return (user==null);
    }
    public boolean usernameIsOk(String username){
        User user = userRepository.findByUsername(username);
        return (user==null);
    }

}
