package com.backend.fileexplorer.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterPayload {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
