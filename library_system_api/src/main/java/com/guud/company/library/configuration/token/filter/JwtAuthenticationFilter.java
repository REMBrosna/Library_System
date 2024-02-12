package com.guud.company.library.configuration.token.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guud.company.library.configuration.security.SecurityConstants;
import com.guud.company.library.configuration.token.JwtTokenBody;
import com.guud.company.library.configuration.token.JwtTokenHeader;
import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.configuration.token.payload.request.AuthTokenRequest;
import com.guud.company.library.configuration.token.payload.response.AuthTokenResponse;
import com.guud.company.library.configuration.token.payload.response.MockUser;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/oauth/token", "POST"));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 1. Get credentials from request
        String username = this.obtainUsername(request);
        username = username != null ? username : "";
        username = username.trim();
        String password = this.obtainPassword(request);
        password = password != null ? password : "";

        AuthTokenRequest authTokenRequest = AuthTokenRequest.builder()
                .username(username)
                .password(password)
                .build();

        // 2. Create auth object (contains credentials) which will be used by auth manager
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authTokenRequest.getUsername(), authTokenRequest.getPassword(), Collections.emptyList());

        // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
        return authenticationManager.authenticate(authToken);
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Long now = System.currentTimeMillis();
        AppUser appUser = (AppUser) authResult.getPrincipal();
        JwtTokenBody jwtAccessToken = JwtTokenBody.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .authorities(appUser.getStrAuthorities())
                .issuedAt(new Date(now))
                .expiredAt(new Date(now + SecurityConstants.ACCESS_TOKNE_EXPIRATION_TIME))
                .build();

        JwtTokenBody jwtRefreshToken = JwtTokenBody.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .authorities(appUser.getStrAuthorities())
                //TODO Will uncomment when code is ready
                //.groupPosition(appUser.getGroupPosition().getValue())
                .issuedAt(new Date(now))
                .expiredAt(new Date(now + SecurityConstants.REFRESH_TOKNE_EXPIRATION_TIME))
                .build();

        AuthTokenResponse authTokenResponse = AuthTokenResponse.builder()
                .jti(UUID.randomUUID().toString())
                .accessToken(generateJwtToken(jwtAccessToken))
                .refreshToken(generateJwtToken(jwtRefreshToken))
                .tokenType("bearer")
                .user(bindingMockUser(appUser))
                .build();

        // Response AuthToken
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(authTokenResponse));
            response.getWriter().flush();
        } finally {
            response.getWriter().close();
        }
    }

    private MockUser bindingMockUser(AppUser appUser){
        Long now = System.currentTimeMillis();
        MockUser mockUser = new MockUser();
        mockUser.setUserId(appUser.getId());
        mockUser.setUsername(appUser.getUsername());
        //mockUser.setDisplayName(appUser.getDisplayName());
        mockUser.setAuthorities(appUser.getStrAuthorities());
        mockUser.setEmail(appUser.getEmail());
        mockUser.setIssuedAt(new Date(now));
        mockUser.setExpiredAt(new Date(now + 15 * 1000));
        return mockUser;
    }

    public String generateJwtToken(JwtTokenBody jwtTokenBody) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        JwtTokenHeader jwtTokenHeader = new JwtTokenHeader();
        ObjectMapper objectMapper = new ObjectMapper();
        String strJwtHeader = Base64.getUrlEncoder().encodeToString(objectMapper.writeValueAsBytes(jwtTokenHeader));
        String strJwtTokenBody = Base64.getUrlEncoder().encodeToString(objectMapper.writeValueAsBytes(jwtTokenBody));
        String strJwtTokenData = String.format("%s.%s", strJwtHeader, strJwtTokenBody);

        byte[] digestData = MessageDigest.getInstance("SHA-512").digest(strJwtTokenData.getBytes());
        String strJwtSignature = Base64.getUrlEncoder().encodeToString(digestData);
        return String.format("%s.%s", strJwtTokenData, strJwtSignature);
    }
}
