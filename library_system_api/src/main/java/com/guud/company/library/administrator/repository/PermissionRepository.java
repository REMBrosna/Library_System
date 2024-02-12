package com.guud.company.library.administrator.repository;

import com.guud.company.library.administrator.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findOneByCode(String code);
}
