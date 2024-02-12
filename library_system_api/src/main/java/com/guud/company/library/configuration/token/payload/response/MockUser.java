package com.guud.company.library.configuration.token.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MockUser {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("issued_at")
    private Date issuedAt;
    @JsonProperty("expired_at")
    private Date expiredAt;
    @JsonProperty("authorities")
    private Collection<String> authorities;
    @JsonProperty("groupPosition")
    private String groupPosition;
    @JsonProperty("displayName")
    private String displayName;
}
