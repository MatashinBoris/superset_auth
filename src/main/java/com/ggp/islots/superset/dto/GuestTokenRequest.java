package com.ggp.islots.superset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GuestTokenRequest {

    private User user;
    private List<Resource> resources;
    private List<RLS> rls;

    @Data
    @AllArgsConstructor
    public static class User {
        private String username;
        private String firstName;
        private String lastName;
    }

    @Data
    @AllArgsConstructor
    public static class Resource {
        private String type;
        private String id;
    }

    @Data
    @AllArgsConstructor
    public static class RLS {
        private String clause;
    }
}
