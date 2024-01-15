package com.katziio.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @JsonIgnore
    private Boolean isAccountDto;
    @JsonIgnore
    private AccountRequestDTO accountDTO;
}
