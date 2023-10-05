package com.backend.fileexplorer.auth;


import com.backend.fileexplorer.auth.entities.AuthenticationResponse;
import com.backend.fileexplorer.auth.entities.UserCredentialsPayload;
import com.backend.fileexplorer.auth.entities.UserRegisterPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody UserRegisterPayload registerUserPayload
    ) {
        return ResponseEntity.ok(authenticationService.register(registerUserPayload));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserCredentialsPayload userCredentialsPayload
    ) {
        return ResponseEntity.ok(authenticationService.login(userCredentialsPayload));
    }
}
