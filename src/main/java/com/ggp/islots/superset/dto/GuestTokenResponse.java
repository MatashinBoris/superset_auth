package com.ggp.islots.superset.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GuestTokenResponse {

    @JsonProperty("token")
    private String token;
}
