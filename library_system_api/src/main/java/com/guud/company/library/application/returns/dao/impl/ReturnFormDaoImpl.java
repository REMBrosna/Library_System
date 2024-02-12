package com.guud.company.library.application.returns.dao.impl;

import com.guud.company.library.application.returns.dao.ReturnFormDao;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.core.GenericDaoImpl;
import org.springframework.stereotype.Service;

@Service("returnFormDao")
public class ReturnFormDaoImpl extends GenericDaoImpl<TReturnForm, String> implements ReturnFormDao {
}
