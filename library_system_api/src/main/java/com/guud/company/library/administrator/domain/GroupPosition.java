package com.guud.company.library.administrator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupPosition {
    ADMIN("ROLE_ADMIN", "Administrator"),
    CUSTOMER("ROLE_CUSTOMER", "Customer");

    private final String code;
    private final String value;
}
