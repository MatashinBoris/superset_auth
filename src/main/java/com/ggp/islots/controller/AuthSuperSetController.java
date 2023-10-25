package com.ggp.islots.controller;

import com.ggp.islots.dto.SuperSetAuthRequest;
import com.ggp.islots.service.SuperSetService;
import com.ggp.islots.superset.dto.GuestTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superset/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthSuperSetController {

    private final SuperSetService superSetService;

    @PostMapping
    public GuestTokenResponse auth(@RequestBody SuperSetAuthRequest request) {

        return superSetService.fetchGuestToken(request);
    }

}
