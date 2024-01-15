package com.katziio.app.service.user;

import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.dto.response.ResponseDTO;
import com.katziio.app.model.Otp;
import com.katziio.app.model.User;
import com.katziio.app.repository.user.OtpRepository;
import com.katziio.app.repository.user.UserRepository;
import com.katziio.app.service.email.EmailSenderService;
import com.katziio.app.util.CustomUtil;
import com.katziio.app.util.enums.Role;
import com.katziio.app.util.helper.CSVHelper;
import com.katziio.app.util.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.*;
@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository; 
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private OtpRepository otpRepository;

    public ResponseDTO createUser(String phone) {
        ResponseDTO response = new ResponseDTO();
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
            user.setRoleList(Arrays.asList(Role.AUTHOR,Role.NOT_REGISTERED));
            user.setAccountList(new ArrayList<>());
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
        response.setErrorDTO(errorDTO);
        return response;
    }

    public ResponseDTO verifyOtp(String phone, String otp) {
        return null;
    }

//    @Override
    public Boolean isValidUser(Long userId) {
        if (!CustomUtil.isValidObject(userId)) {
            return false;
        }
        return this.userRepository.existsById(userId);
    }

//    @Override
    public User getAccountById(Long userId) {
        if (!CustomUtil.isValidObject(userId)) {
            return null;
        }
        Optional<User> userOptional = this.userRepository.findById(userId);
        return userOptional.orElse(null);
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

    public List<User> readFile(MultipartFile file) {
        if (!CSVHelper.hasCSVFormat(file)) {
            System.out.println("not the expected file");
            return null;
        }
        try {
            List<User> userList = CSVHelper.csvToUser(file.getInputStream());
            userRepository.saveAll(userList);
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void writeUserToCsv(PrintWriter writer) {
        CSVHelper.writeUserToCsv(writer, userRepository.findAll());
    }

    public void writeUserToCsvCustom(PrintWriter writer) {
        CSVHelper.writeUserToCsv(writer, userRepository.findAll());
    }

}
