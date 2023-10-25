package com.ggp.islots.service;

import com.ggp.islots.dto.SuperSetAuthRequest;
import com.ggp.islots.superset.dto.GuestTokenRequest;
import com.ggp.islots.superset.dto.GuestTokenResponse;
import com.ggp.islots.superset.dto.LoginRequest;
import com.ggp.islots.superset.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperSetService {

    private final RestTemplate restTemplate;

    public LoginResponse adminAuth() {

        String url = "http://localhost:8080/api/v1/security/login";

        LoginRequest loginRequest = LoginRequest.builder()
                .username("admin")
                .password("admin")
                .refresh(true)
                .provider("db")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<LoginResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, LoginResponse.class);

        return responseEntity.getBody();
    }

    public GuestTokenResponse fetchGuestToken(SuperSetAuthRequest request) {

        LoginResponse loginResponse = adminAuth();
        String url = "http://localhost:8080/api/v1/security/guest_token/";
        String clientCode = request.getClientCode() == null ? "demo" : request.getClientCode();

        GuestTokenRequest guestTokenRequest = GuestTokenRequest.builder()
                .user(new GuestTokenRequest.User("boris", "boris", "boris"))
                .resources(List.of(new GuestTokenRequest.Resource("dashboard", request.getDashboardId())))
                .rls(List.of(new GuestTokenRequest.RLS("client_code='" + clientCode + "'")))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + loginResponse.getAccessToken());
        HttpEntity<GuestTokenRequest> requestEntity = new HttpEntity<>(guestTokenRequest, headers);

        ResponseEntity<GuestTokenResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, GuestTokenResponse.class);
        return responseEntity.getBody();
    }

}
