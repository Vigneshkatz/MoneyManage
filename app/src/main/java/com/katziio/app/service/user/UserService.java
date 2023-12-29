package com.katziio.app.service.user;

import com.katziio.app.dto.Response;

public interface UserService {

    Response createUser(String phone);

    Response verifyOtp(String phone, String otp);
}
