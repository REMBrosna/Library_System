package com.guud.company.library.reports.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.reports.dao.ReportServiceConfigDao;
import com.guud.company.library.reports.model.TReportServiceConfig;
import org.springframework.stereotype.Service;

@Service("reportServiceConfigDao")
public class ReportServiceConfigDaoImpl extends GenericDaoImpl<TReportServiceConfig, Long> implements ReportServiceConfigDao {
}
