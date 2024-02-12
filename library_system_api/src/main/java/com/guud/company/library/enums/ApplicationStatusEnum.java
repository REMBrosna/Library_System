package com.guud.company.library.enums;

import com.guud.company.library.master.utils.MasterServiceEntity;

import java.util.Arrays;
import java.util.Optional;

public enum ApplicationStatusEnum {

    DRF("DRAFT", "DRAFT"),
    SUB("SUBMIT", "SUBMITTED"),
    BOR("BORROW", "BORROWED"),
    RET("RETURN", "RETURNED"),
    OWE("OWE", "OWED"),
    OVR("OVER", "OVER");

    private final String desc;
    private final String post;

    ApplicationStatusEnum(String desc, String post) {
        this.desc = desc;
        this.post = post;
    }

    public static Optional<MasterServiceEntity> isExisting(String entityName) {
        return Arrays.stream(MasterServiceEntity.values())
                .filter(env -> env.name().equals(entityName))
                .findFirst();
    }

    public String getDesc() {
        return desc;
    }

    public String getPost() {
        return post;
    }
}
