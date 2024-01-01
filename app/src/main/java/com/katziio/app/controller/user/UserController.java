package com.katziio.app.controller.user;

import com.katziio.app.dto.response.Response;
import com.katziio.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response createUser(@RequestBody String phone){
        System.out.println(phone);
        return userService.createUser(phone);
    }

    @PostMapping("/verify-otp/{phone}/{otp}")
    public Response verifyUser(@RequestParam("phone") String phone, @RequestParam("otp") String otp){
        return this.userService.verifyOtp(phone,otp);
    }

}
