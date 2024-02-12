package com.guud.company.library.master.utils;

import java.util.Arrays;
import java.util.Optional;

public enum MasterServiceEntity {
    MST_PROVINCE("province", "com.guud.company.library.master.province.dto.MstProvince", "provinceService"),
    MST_DISTRICT("district", "com.guud.company.library.master.district.dto.District", "districtService"),
    MST_COMMUNE("commune", "com.guud.company.library.master.commune.dto.Commune", "communeService"),
    MST_BOOK("book", "com.guud.company.library.master.book.dto.Book", "bookService"),
    T_CUSTOMER("customer", "com.guud.company.library.master.users.dto.Customer", "customerService"),
    MST_ITEM("itm", "com.guud.company.library.application.item.dto.Items", "itemService"),
    MST_RETURN("rtn", "com.guud.company.library.application.returns.dto.Return", "returnService"),
    MST_APPLICATION("application", "com.guud.company.library.application.apps.dto.Application", "applicationService"),
    MST_AUDIT_LOG("auditLog", "com.guud.company.library.audit.dto.AuditLog", "auditLogService");


    private final String entityName;
    private final String entityDTOs;
    private final String entityServices;

    MasterServiceEntity(String entityName, String entityDTOs, String entityServices) {
        this.entityName = entityName;
        this.entityServices = entityServices;
        this.entityDTOs = entityDTOs;
    }

    public static Optional<MasterServiceEntity> isExisting(String entityName) {
        return Arrays.stream(MasterServiceEntity.values())
                .filter(env -> env.entityName.equals(entityName))
                .findFirst();
    }

    public static MasterServiceEntity getMasterServiceEntityByEntityName(String entityName) {
        return Arrays.stream(MasterServiceEntity.values())
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
