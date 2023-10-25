package com.ggp.islots.controller;

import com.ggp.islots.dto.SuperSetAuthRequest;
import com.ggp.islots.service.SuperSetService;
import com.ggp.islots.superset.dto.GuestTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/superset/auth")
@RequiredArgsConstructor
public class AuthSuperSetController {

    private final SuperSetService superSetService;

    @PostMapping
    public ResponseEntity<GuestTokenResponse> auth(@RequestBody SuperSetAuthRequest request) {

        return ResponseEntity.ok(superSetService.fetchGuestToken(request));
    }

}
