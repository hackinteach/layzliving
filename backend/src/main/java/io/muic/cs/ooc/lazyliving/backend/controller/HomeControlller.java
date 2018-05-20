package io.muic.cs.ooc.lazyliving.backend.controller;

import io.muic.cs.ooc.lazyliving.backend.entity.User;
import io.muic.cs.ooc.lazyliving.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeControlller {

    @Autowired
    private UserService userService;


}
