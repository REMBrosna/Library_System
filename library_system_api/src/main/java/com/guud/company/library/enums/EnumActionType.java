package com.guud.company.library.enums;

public enum EnumActionType {

    OVR("OVER", "OVER");

    private final String desc;
    private final String postDesc;

    EnumActionType(String desc, String postDesc) {
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
