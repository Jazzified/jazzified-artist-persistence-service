package dev.tobiadegbuji.artistpersistenceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private String access_token;
    private String token_type;
    private Integer expires_in;
}