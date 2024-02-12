package com.guud.company.library.administrator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guud.company.library.infrastructure.domain.AbstractPersistableCustom;
import com.guud.company.library.utils.DateUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Slf4j
@Entity
@Table(name = "appuser", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}, name = "username_org"))
public class AppUser extends AbstractPersistableCustom implements UserDetails {

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nonexpired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "nonlocked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "nonexpired_credentials", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "firsttime_login_remaining", nullable = false)
    private boolean firstTimeLoginRemaining;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "m_appuser_role",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "group_position")
    @Enumerated(EnumType.STRING)
    private GroupPosition groupPosition;

    @Column(name = "last_time_password_updated")
    @Temporal(TemporalType.DATE)
    private Date lastTimePasswordUpdated;

    @Column(name = "password_never_expires", nullable = false)
    private boolean passwordNeverExpires;

    @Column(name = "is_self_service_user", nullable = false)
    private boolean isSelfServiceUser;

    @Column(name = "cannot_change_password")
    private Boolean cannotChangePassword;

    @Column(name = "chat_id")
    private String chatId;

    public AppUser() {
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.accountNonExpired = true;
        this.firstTimeLoginRemaining = true;
        this.enabled = true;
        this.roles = new HashSet<>();
    }

    public boolean isSelfServiceUser() {
        return this.isSelfServiceUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void updatePassword(final String encodePassword) {
        if (cannotChangePassword != null && cannotChangePassword == true) {
            throw new RuntimeException("Password of this user may not be modified");
        }

        this.password = encodePassword;
        this.firstTimeLoginRemaining = false;
        this.lastTimePasswordUpdated = DateUtils.getDateOfTenant();

    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return populateGrantedAuthorities();
    }

    private List<GrantedAuthority> populateGrantedAuthorities() {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (final Role role : this.roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            try {
                final Collection<Permission> permissions = role.getPermissions();
                for (final Permission permission : permissions) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getCode()));
                }
            } catch (Exception ex) {}
        }
        return grantedAuthorities;
    }

    @JsonIgnore
    public List<String> getStrAuthorities() {
        final List<String> grantedAuthorities = new ArrayList<>();
        for (final Role role : this.roles) {
            grantedAuthorities.add(role.getName());
            final Collection<Permission> permissions = role.getPermissions();
            for (final Permission permission : permissions) {
                grantedAuthorities.add(permission.getCode());
            }
        }
        return grantedAuthorities;
    }

    public void setStrRoles(List<String> roles) {
        for (final String role : roles) {
            this.roles.add(Role.builder()
                    .name(role)
                    .build());
        }
    }

 /*   public String getDisplayName() {
        String firstName = StringUtils.isNotBlank(this.firstname) ? this.firstname : "";
        if (StringUtils.isNotBlank(this.lastname)) {
            return firstName + " " + this.lastname;
        }
        return firstName;
    }*/

    @Override
    public String toString() {
        return "AppUser [username=" + this.username + ", getId()=" + this.getId() + "]";
    }

}
