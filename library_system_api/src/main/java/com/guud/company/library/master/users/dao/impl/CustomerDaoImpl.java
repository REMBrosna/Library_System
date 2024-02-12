package com.guud.company.library.master.users.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.users.dao.CustomerDao;
import com.guud.company.library.master.users.model.TCustomer;
import org.springframework.stereotype.Service;

@Service("customerDao")
public class CustomerDaoImpl extends GenericDaoImpl<TCustomer, String> implements CustomerDao {
}
