package com.guud.company.library.administrator.domain;

import com.guud.company.library.infrastructure.domain.AbstractPersistableCustom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission extends AbstractPersistableCustom implements Serializable {

    @Column(name = "grouping", nullable = false, length = 45)
    private String grouping;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "entity_name", length = 100)
    private String entityName;

    @Column(name = "action_name", length = 100)
    private String actionName;

    @Column(name = "can_maker_checker", nullable = false)
    private boolean canMakerChecker;

    public Permission(final String grouping, final String entityName, final String actionName) {
        this.grouping = grouping;
        this.entityName = entityName;
        this.actionName = actionName;
        this.code = actionName + "_" + entityName;
        this.canMakerChecker = false;
    }

    protected Permission() {
        this.grouping = null;
        this.entityName = null;
        this.actionName = null;
        this.code = null;
        this.canMakerChecker = false;
    }

    public boolean hasCode(final String checkCode) {
        return this.code.equalsIgnoreCase(checkCode);
    }

    public String getCode() {
        return this.code;
    }

    public boolean hasMakerCheckerEnabled() {
        return this.canMakerChecker;
    }

    public String getGrouping() {
        return this.grouping;
    }

    public boolean enableMakerChecker(final boolean canMakerChecker) {
        final boolean isUpdatedValueSame = this.canMakerChecker == canMakerChecker;
        this.canMakerChecker = canMakerChecker;
        return !isUpdatedValueSame;
    }
}
