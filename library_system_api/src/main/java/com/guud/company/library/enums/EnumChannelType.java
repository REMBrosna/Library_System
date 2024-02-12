package com.guud.company.library.enums;

public enum EnumChannelType {
    TELEGRAM("TELEGRAM", "TELEGRAM"),
    EMAIL("EMAIL", "EMAIL");

    private final String desc;
    private final String postDesc;

    EnumChannelType(String desc, String postDesc) {
        this.desc = desc;
        this.postDesc = postDesc;
    }

    public String getDesc() {
        return desc;
    }

    public String getPostDesc() {
        return postDesc;
    }
}
