package com.guud.company.library.application.utils;

import java.util.Arrays;
import java.util.Optional;

public enum ApplicationServiceEntity {
    APP_BORROW("bor", "com.guud.company.library.application.borrow.dto.BorrowForm", "borrowApplication"),
    APP_RETURNS("ret", "com.guud.company.library.application.returns.dto.ReturnForm", "returnApplication");

    private final String entityName;
    private final String entityDTOs;
    private final String entityServices;

    ApplicationServiceEntity(String entityName, String entityDTOs, String entityServices) {
        this.entityName = entityName;
        this.entityServices = entityServices;
        this.entityDTOs = entityDTOs;
    }

    public static Optional<ApplicationServiceEntity> isExisting(String entityName) {
        return Arrays.stream(ApplicationServiceEntity.values())
                .filter(env -> env.entityName.equals(entityName))
                .findFirst();
    }

    public static ApplicationServiceEntity getMasterServiceEntityByEntityName(String entityName) {
        return Arrays.stream(ApplicationServiceEntity.values())
                .filter(env -> env.entityName.equals(entityName))
                .findFirst().orElse(null);
    }

    public String getEntityName() {
        return entityName;
    }

    public String getEntityServices() {
        return entityServices;
    }

    public String getEntityDTOs() {
        return entityDTOs;
    }
}
