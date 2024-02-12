package com.guud.company.library.master.district.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.district.dao.DistrictDao;
import com.guud.company.library.master.district.model.TMstDistrict;
import org.springframework.stereotype.Service;

@Service("DistrictDao")
public class DistrictDaoImpl extends GenericDaoImpl<TMstDistrict, String> implements DistrictDao {
}
