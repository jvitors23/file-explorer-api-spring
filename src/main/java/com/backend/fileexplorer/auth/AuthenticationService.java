package com.backend.fileexplorer.auth;

import com.backend.fileexplorer.auth.entities.AuthenticationResponse;
import com.backend.fileexplorer.auth.entities.UserCredentialsPayload;
import com.backend.fileexplorer.auth.entities.UserRegisterPayload;
import com.backend.fileexplorer.auth.jwt.JwtManager;
import com.backend.fileexplorer.entities.Role;
import com.backend.fileexplorer.repos.UserRepository;
import com.backend.fileexplorer.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserRegisterPayload registerUserPayload) {
        User user = User.builder()
            .firstName(registerUserPayload.getFirstName())
            .lastName(registerUserPayload.getLastName())
            .email(registerUserPayload.getEmail())
            .password(passwordEncoder.encode(registerUserPayload.getPassword()))
            .role(Role.USER)
            .build();
        userRepository.save(user);
        String jwtToken = jwtManager.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse login(UserCredentialsPayload userCredentialsPayload) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userCredentialsPayload.getEmail(),
                userCredentialsPayload.getPassword()
            )
        );
        User user = userRepository.findByEmail(userCredentialsPayload.getEmail())
                .orElseThrow();
        String jwtToken = jwtManager.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
