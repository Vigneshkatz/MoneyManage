package com.katziio.app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katziio.app.dto.error.ErrorDTO;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @JsonProperty("content")
    private Object content;
    @JsonProperty("response")
    private ErrorDTO response;
    @JsonProperty("emptyField")
    private List<String> emptyField;
}