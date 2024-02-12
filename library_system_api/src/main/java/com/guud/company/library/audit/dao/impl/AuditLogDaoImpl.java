package com.guud.company.library.audit.dao.impl;

import com.guud.company.library.audit.dao.AuditLogDao;
import com.guud.company.library.audit.model.TAuditLog;
import com.guud.company.library.core.GenericDaoImpl;
import org.springframework.stereotype.Service;

@Service("auditLogDao")
public class AuditLogDaoImpl  extends GenericDaoImpl<TAuditLog, String> implements AuditLogDao {
}
