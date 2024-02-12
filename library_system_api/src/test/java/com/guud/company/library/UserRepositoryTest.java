package com.guud.company.library;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.administrator.domain.Role;
import com.guud.company.library.administrator.repository.AppUserRepository;
import com.guud.company.library.administrator.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldAddNewAdministratorUserSuccess() {
        Role role = roleRepository.findRoleByName("ROLE_ADMIN");
        AppUser user = new AppUser();
        user.setLastname("Nuy");
        user.setFirstname("Vannet");
        user.setEmail("ADMIN");
        user.setUsername("ADMIN");
        user.setPassword(passwordEncoder.encode("1234"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        appUserRepository.save(user);
        System.out.println(user);
    }

}

