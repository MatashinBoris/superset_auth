package com.ggp.islots.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class SuperSetAuthRequest {

    @Nullable
    private String login;
    @Nullable
    private String password;
    private String dashboardId;
    @Nullable
    private String clientCode;
}
