package com.guud.company.library.configuration.token.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenRequest {
    @JsonProperty("username")
    @NotEmpty(message = "Username is mandatory")
    private String username;
    @JsonProperty("password")
    @NotEmpty(message = "Password is mandatory")
    private String password;
}
