package com.guud.company.library;

import com.guud.company.library.administrator.domain.Role;
import com.guud.company.library.administrator.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldAddNewRoleAdministratorSuccess() {
        Role role = new Role("ROLE_ADMIN", "Customer");
        role.setDisabled(false);
        Role newRole = roleRepository.save(role);
        System.out.println(newRole);
    }
}

