package com.guud.company.library.configuration.principal;
import com.guud.company.library.administrator.domain.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountPrincipalService {

    public AppUser getAccountPrincipal() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
