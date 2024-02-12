package com.guud.company.library.reports.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.reports.dao.ReportTemplateConfigDao;
import com.guud.company.library.reports.model.TReportTemplateConfig;
import org.springframework.stereotype.Service;

@Service("reportTemplateConfigDao")
public class ReportTemplateConfigDaoImpl extends GenericDaoImpl<TReportTemplateConfig, Long> implements ReportTemplateConfigDao {
}
