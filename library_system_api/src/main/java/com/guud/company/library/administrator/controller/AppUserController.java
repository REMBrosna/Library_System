package com.guud.company.library.administrator.controller;

import com.guud.company.library.administrator.repository.AppUserRepository;
import com.guud.company.library.configuration.principal.AccountPrincipalService;
import com.guud.company.library.infrastructure.api.ApiResponse;
import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.administrator.service.UserDetailsServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/v1/library/administrator")
@CrossOrigin
@RestController
@Log4j2
public class AppUserController {

    @Autowired
    private UserDetailsServiceImpl service;
    @Autowired
    private AccountPrincipalService principalService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUser() {
        return ResponseEntity.ok().body(appUserRepository.findAll());
    }
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByName(@PathVariable String userName) {
        ApiResponse<Object> serviceStatus = new ApiResponse<>();
        try {
            AppUser accountPrincipal = principalService.getAccountPrincipal();
            if (Objects.isNull(accountPrincipal)) {
                serviceStatus.setMessage("AccountPrincipal doesn't exist");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (StringUtils.isBlank(userName)) {
                serviceStatus.setMessage("UserId is null");
                serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            serviceStatus.setCode(ApiResponse.OK);
            serviceStatus.setData(service.loadUserByUsername(userName));
            return new ResponseEntity<>(serviceStatus, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("getUserById error", ex);
            serviceStatus.setMessage(ex.getMessage());
            serviceStatus.setCode(ApiResponse.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(serviceStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
