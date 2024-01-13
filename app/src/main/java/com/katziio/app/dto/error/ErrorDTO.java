package com.katziio.app.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDTO {
    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("empty_field")
    private List<String> emptyField;
    @JsonProperty("isEmptyFields")
    private Boolean isEmptyField;
}