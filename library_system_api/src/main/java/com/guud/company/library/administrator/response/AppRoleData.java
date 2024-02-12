package com.guud.company.library.administrator.response;

public interface AppRoleData {

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
}
