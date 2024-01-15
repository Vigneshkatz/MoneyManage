package com.katziio.app.controller.user;

import com.katziio.app.dto.request.UserRequestDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/login")
    public ResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }

    @PostMapping("/verify-otp/{phone}/{otp}")
    public ResponseDTO verifyUser(@RequestParam("phone") String phone, @RequestParam("otp") String otp){
        return this.userService.verifyOtp(phone,otp);
    }

}
