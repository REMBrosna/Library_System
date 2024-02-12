package com.guud.company.library.enums;

public enum ApplicationTypeEnum {

    APP("APP"),
    BOR("BORROW"),
    RET("RETURN"),
    ITM("ITEM"),
    CUS("CUSTOMER");

    private final String desc;

    ApplicationTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
