package com.guud.company.library.configuration.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenBody {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonIgnore
    private String fullName;
    @JsonProperty("roles")
    private List<String> authorities;
    @JsonProperty("groupPosition")
    private String groupPosition;
    @JsonProperty("issued_at")
    private Date issuedAt;
    @JsonProperty("expired_at")
    private Date expiredAt;
}
