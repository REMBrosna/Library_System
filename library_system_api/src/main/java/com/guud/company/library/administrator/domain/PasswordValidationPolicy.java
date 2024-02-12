package com.guud.company.library.administrator.domain;
import com.guud.company.library.infrastructure.domain.AbstractPersistableCustom;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "password_validation_policy")
public class PasswordValidationPolicy extends AbstractPersistableCustom {

    @Column(name = "regex", nullable = false)
    private String regex;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    public PasswordValidationPolicy(final String regex, final String description, final boolean active) {
        this.description = description;
        this.regex = regex;
        this.active = active;
    }

    public PasswordValidationPolicy() {
        this.active = false;
    }

    public Map<String, Object> activate() {
        final Map<String, Object> actualChanges = new LinkedHashMap<>(1);

        final String active = "active";

        if (!this.active) {

            actualChanges.put(active, true);
            this.active = true;
        }

        return actualChanges;
    }

    public boolean isActive() {
        return this.active;
    }

    public void deActivate() {
        this.active = false;
    }

}
