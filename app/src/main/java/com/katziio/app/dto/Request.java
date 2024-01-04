package com.katziio.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @JsonIgnore
    private Boolean isAccountDto;
    @JsonIgnore
    private AccountDTO accountDTO;
}
