package com.katziio.app.service.user;

import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.dto.response.Response;
import com.katziio.app.model.Otp;
import com.katziio.app.model.User;
import com.katziio.app.repository.user.OtpRepository;
import com.katziio.app.repository.user.UserRepository;
import com.katziio.app.service.email.EmailSenderService;
import com.katziio.app.util.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private OtpRepository otpRepository;
    public Response createUser(String phone) {
        Response response = new Response();
        ErrorDTO errorDTO = new ErrorDTO();
//        UserDTO userDTO = new UserDTO();
        if (phone.isBlank()) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("number should not be null");
        }

        if (phone.length() != 10) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("Enter a valid number");
        }

        Optional<User> userOptional = Optional.empty();
        try {
            userOptional = userRepository.findByPhone(phone);
        } catch (Exception e) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("User not found");
        }
        if (userOptional != null && userOptional.isPresent()) {

            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("user already exists");
        } else {
            User user = new User();
            user.setPhone(phone);
            user.setEmail("vignesh000129@gmail.com");
            user.setIsVerified(false);
            try {
                User dbUser = userRepository.save(user);
                String otp = UserUtil.createOTP();
                try {
                    createOtp(user, otp);
                    emailSenderService.initiateEmail(user.getEmail(), otp);
                    response.setContent(dbUser);
                    errorDTO.setErrorCode(0);
                    errorDTO.setErrorMessage("New User created Successfully");
                    return response;
                } catch (Exception e) {
                    errorDTO.setErrorCode(1);
                    errorDTO.setErrorMessage(e.getMessage());
                }
            } catch (Exception e) {
                errorDTO.setErrorCode(2);
                errorDTO.setErrorMessage("User not saved \n internal error");
            }
        }
        response.setContent(null);
        response.setResponse(errorDTO);
        return response;
    }

    public Response verifyOtp(String phone, String otp) {
        return null;
    }

    private void createOtp(User user, String otpString) {
        Otp otp = new Otp();
        otp.setUser(user);
        otp.setOtp_attempts(0);
        otp.setOtp_generated(otpString);
        otp.setPhone(user.getPhone());
        otp.setEmail(user.getEmail());
        otp.setOtp_created_at(Calendar.getInstance().getTime());
        this.otpRepository.save(otp);
    }

}
