package com.katziio.app.dto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    @Nullable
    private Boolean isAccountDto;
    @Nullable
    private AccountRequestDTO accountDTO;
}
