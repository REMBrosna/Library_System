package com.guud.company.library.application.borrow.dao.impl;

import com.guud.company.library.application.borrow.dao.BorrowFormDao;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.core.GenericDaoImpl;
import org.springframework.stereotype.Service;

@Service("borrowFormDao")
public class BorrowFormDaoImpl extends GenericDaoImpl<TBorrowForm, String> implements BorrowFormDao {
}
