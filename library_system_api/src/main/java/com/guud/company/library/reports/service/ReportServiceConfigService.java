package com.guud.company.library.reports.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.reports.model.TReportServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceConfigService {

    @Autowired
    @Qualifier("reportServiceConfigDao")
    protected GenericDao<TReportServiceConfig, Long> reportServiceConfigDao;

    public TReportServiceConfig getReportServiceByValAndServiceName(String val, String serviceName) throws Exception {
        String sql = "SELECT o FROM TReportServiceConfig o WHERE o.resVal =:resVal AND o.resServiceName =:resServiceName AND o.resRecStatus ='A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("resVal", val);
        param.put("resServiceName", serviceName);
        List<TReportServiceConfig> list = reportServiceConfigDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
