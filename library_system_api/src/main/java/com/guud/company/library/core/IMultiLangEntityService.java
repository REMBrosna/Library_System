package com.guud.company.library.core;

import com.guud.company.library.administrator.domain.AppUser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IMultiLangEntityService<D> {

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    D addML(D var1, AppUser var2) throws Exception;

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    D updateML(D var1, AppUser var2) throws Exception;

    D findML(D var1) throws Exception;
}
