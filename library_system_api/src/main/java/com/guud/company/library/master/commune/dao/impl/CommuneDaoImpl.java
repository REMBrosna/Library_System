package com.guud.company.library.master.commune.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.master.commune.dao.CommuneDao;
import com.guud.company.library.master.commune.model.TMstCommune;
import org.springframework.stereotype.Service;

@Service("CommuneDao")
public class CommuneDaoImpl extends GenericDaoImpl<TMstCommune, String> implements CommuneDao {
}
