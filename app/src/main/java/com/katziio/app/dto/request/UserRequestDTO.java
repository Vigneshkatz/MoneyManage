package com.katziio.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.katziio.app.model.Account;
import com.katziio.app.util.enums.Role;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String userName;
    @JsonIgnore
    private Boolean isPremium;
    private String email;
    private String phone;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Boolean isVerified;
    @JsonIgnore
    private List<AccountRequestDTO> accountList;
}
