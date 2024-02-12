package com.guud.company.library.sysparam.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.sysparam.model.TSystemParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SystemParamService {
    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(SystemParamService.class);

    @Autowired
    @Qualifier("systemParamDao")
    protected GenericDao<TSystemParam, String> systemParamDao;

    public TSystemParam getSystemParamByKey(String key) throws Exception {
        String sql = "SELECT o FROM TSystemParam o WHERE o.sypKey =:sypKey AND o.sypRecStatus = 'A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("sypKey", key);
        List<TSystemParam> list = systemParamDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public String getValue(String key, String defaultValue) {
        try {
            TSystemParam systemParam = getSystemParamByKey(key);
            if (Objects.nonNull(systemParam)) {
                return systemParam.getSypVal();
            }
        } catch (Exception e) {
            log.error("getValue", e);
        }

        return defaultValue;
    }
}
