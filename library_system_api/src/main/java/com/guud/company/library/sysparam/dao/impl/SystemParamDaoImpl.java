package com.guud.company.library.sysparam.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.sysparam.dao.SystemParamDao;
import com.guud.company.library.sysparam.model.TSystemParam;
import org.springframework.stereotype.Service;

@Service("systemParamDao")
public class SystemParamDaoImpl extends GenericDaoImpl<TSystemParam, String> implements SystemParamDao {
}
