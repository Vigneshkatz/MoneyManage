package com.katziio.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @JsonProperty("content")
    private Object content;
    @JsonProperty("response")
    private ErrorDTO response;
}