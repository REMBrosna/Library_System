package com.guud.company.library.master.province.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.province.dao.MstProvinceDao;
import com.guud.company.library.master.province.model.TMstProvince;
import org.springframework.stereotype.Service;

@Service("mstProvinceDao")
public class MstProvinceDaoImpl extends GenericDaoImpl<TMstProvince, String> implements MstProvinceDao {
}
