package com.guud.company.library.reports.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.reports.model.TReportTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportTemplateConfigService {

    @Autowired
    @Qualifier("reportTemplateConfigDao")
    protected GenericDao<TReportTemplateConfig, Long> reportTemplateConfigDao;

    public List<TReportTemplateConfig> getReportTemplateByServiceId(Long retService) throws Exception {
        String sql = "SELECT o FROM TReportTemplateConfig o WHERE o.retService.resId =:retService AND o.retCecStatus = 'A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("retService", retService);
        return reportTemplateConfigDao.getByQuery(sql, param);
    }
}
