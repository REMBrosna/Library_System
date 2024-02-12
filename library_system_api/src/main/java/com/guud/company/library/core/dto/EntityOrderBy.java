package com.guud.company.library.core.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityOrderBy {

    private String attribute;
    private EntityOrderBy.ORDERED ordered;

    public static enum ORDERED {
        ASC,
        DESC;

        private ORDERED() {
        }
    }

    public String toString() {
        return " order by o." + this.attribute + " " + this.ordered.toString().toLowerCase();
    }
}
