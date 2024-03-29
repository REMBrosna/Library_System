package com.guud.company.library.administrator.response;

import com.guud.company.library.administrator.domain.GroupPosition;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

public interface AppUserData {

    Long getId();

    String getEmail();

    String getUsername();

    String getFirstname();

    String getLastname();

    boolean getAccountNonExpired();

    boolean getAccountNonLocked();

    boolean getCredentialsNonExpired();

    boolean getEnabled();

    boolean getFirstTimeLoginRemaining();

    boolean getDeleted();

    GroupPosition getGroupPosition();

    Collection<GrantedAuthority> getAuthorities();

    List<String> getStrAuthorities();

    String getDisplayName();
}
