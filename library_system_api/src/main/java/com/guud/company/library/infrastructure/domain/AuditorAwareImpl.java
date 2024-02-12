package com.guud.company.library.infrastructure.domain;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.administrator.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<AppUser> {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public Optional<AppUser> getCurrentAuditor() {
        Optional<AppUser> currentUser;
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            final Authentication authentication = securityContext.getAuthentication();
            try {
                if (authentication != null) {
                    currentUser = Optional.ofNullable((AppUser) authentication.getPrincipal());
                } else {
                    currentUser = retrieveSuperUser();
                }
            }catch (Exception ex) {
                // ex.printStackTrace();
                currentUser = retrieveSuperUser();
            }
        } else {
            currentUser = retrieveSuperUser();
        }
        return currentUser;
    }

    private Optional<AppUser> retrieveSuperUser() {
        return this.userRepository.findById(Long.valueOf("1"));
    }
}
