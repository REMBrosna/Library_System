package com.guud.company.library.configuration.token.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenResponse {
    @JsonProperty("jti")
    private String jti;
    @JsonProperty("user")
    private MockUser user;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
}
