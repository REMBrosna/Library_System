package com.guud.company.library.notification.configure.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.enums.EnumActionType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.notification.configure.model.TNotificationApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationApplicationService {

    @Autowired
    @Qualifier("notificationApplicationDao")
    protected GenericDao<TNotificationApplication, Long> notificationApplicationDao;

    public TNotificationApplication newNotificationApplication(TMstApplicationType applicationType, EnumActionType action){
        TNotificationApplication tNotificationApplication = new TNotificationApplication();
        tNotificationApplication.setTMstApplicationType(applicationType);
        tNotificationApplication.setNoaAction(action.name());
        tNotificationApplication.setNoaRecStatus('A');
        return tNotificationApplication;
    }

    public void create(TNotificationApplication notificationApplication) throws Exception {
        notificationApplicationDao.saveOrUpdate(notificationApplication);
    }

    public TNotificationApplication getTNotificationApplicationByAppTypeAndAppAction(String appType, String appAction) throws Exception {
        String sql = "SELECT o FROM TNotificationApplication o WHERE o.TMstApplicationType.aptCode =:appType AND o.noaAction =:appAction AND o.noaRecStatus = 'A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("appType", appType);
        param.put("appAction", appAction);
        List<TNotificationApplication> list = notificationApplicationDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
