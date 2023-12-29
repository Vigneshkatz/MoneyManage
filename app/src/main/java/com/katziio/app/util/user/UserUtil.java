package com.katziio.app.util.user;

import java.security.SecureRandom;

public class UserUtil {
    final static int MIN_OTP = 0;
    final static  int MAX_OTP = 9999;
    public static String createOTP(){
        SecureRandom secureRandom = new SecureRandom();
        int otp = secureRandom.nextInt(MAX_OTP - MIN_OTP + 1);
        String formattedOtp = String.format("%04d", otp);
        return formattedOtp;
    }
}
