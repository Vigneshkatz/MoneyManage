package com.katziio.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katziio.app.dto.error.ErrorDTO;
import com.katziio.app.util.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    @JsonProperty("content")
    private Object content;
    @JsonProperty("response")
    private ErrorDTO errorDTO;
    @JsonProperty("error-code")
    private ErrorEnum errorEnum;
}