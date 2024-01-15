package com.katziio.app.service.user;

import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    ResponseDTO createUser(String phone);

    ResponseDTO verifyOtp(String phone, String otp);

    Boolean isValidUser(Long userId);

    User getAccountById(Long userId);
}
