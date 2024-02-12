package com.guud.company.library.application.apps.dao.impl;

import com.guud.company.library.application.apps.dao.ApplicationDao;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.core.GenericDaoImpl;
import org.springframework.stereotype.Service;

@Service("applicationDao")
public class ApplicationDaoImpl extends GenericDaoImpl<TApplication, String> implements ApplicationDao {
}
