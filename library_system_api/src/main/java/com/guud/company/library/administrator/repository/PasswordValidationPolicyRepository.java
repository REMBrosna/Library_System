package com.guud.company.library.administrator.repository;

import com.guud.company.library.administrator.domain.PasswordValidationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordValidationPolicyRepository
        extends JpaRepository<PasswordValidationPolicy, Long>, JpaSpecificationExecutor<PasswordValidationPolicy> {

    @Query("select PVP from PasswordValidationPolicy PVP WHERE PVP.active = true")
    PasswordValidationPolicy findActivePasswordValidationPolicy();

}
