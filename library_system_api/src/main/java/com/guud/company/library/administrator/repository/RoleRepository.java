package com.guud.company.library.administrator.repository;

import com.guud.company.library.administrator.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query("SELECT COUNT(a) FROM AppUser a JOIN a.roles r WHERE r.id = :roleId AND a.deleted = false")
    Integer findCountOfRolesAssociatedWithUsers(@Param("roleId") Long roleId);

    @Query("SELECT role FROM Role role WHERE role.name = :name")
    Role findRoleByName(@Param("name") String name);

}
