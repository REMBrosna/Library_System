package com.guud.company.library.master.appType.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.appType.dao.ApplicationTypeDao;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.master.book.dao.BookDao;
import com.guud.company.library.master.book.model.TBook;
import org.springframework.stereotype.Service;

@Service("applicationTypeDao")
public class ApplicationTypeDaoImpl extends GenericDaoImpl<TMstApplicationType, String> implements ApplicationTypeDao {

}
