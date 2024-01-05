package com.katziio.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.util.enums.ErrorEnum;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    @JsonProperty("content")
    private Object content;
    @JsonProperty("response")
    private ErrorDTO response;
    @JsonProperty("error-code")
    private ErrorEnum errorEnum;
}