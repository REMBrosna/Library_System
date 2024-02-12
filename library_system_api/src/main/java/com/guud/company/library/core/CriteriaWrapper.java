package com.guud.company.library.core;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

public interface CriteriaWrapper {

    Criteria criteria(Criteria var1, SessionFactory var2, Class<?> var3);
}
