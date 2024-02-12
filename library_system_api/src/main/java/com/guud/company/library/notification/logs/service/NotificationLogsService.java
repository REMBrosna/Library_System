package com.guud.company.library.notification.logs.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.enums.EnumChannelType;
import com.guud.company.library.notification.logs.model.TNotificationLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationLogsService {

    @Autowired
    @Qualifier("notificationLogsDao")
    protected GenericDao<TNotificationLogs, Long> notificationLogsDao;

    public void create(TNotificationLogs notificationLogs) throws Exception {
        notificationLogsDao.saveOrUpdate(notificationLogs);
    }

    public List<TNotificationLogs> getAllPendingAndFailedNotification(int retry) throws Exception {
        String sql = "SELECT o FROM TNotificationLogs o WHERE o.nolRetry <=:nolRetry AND o.nolStatus IN ('P', 'F') AND o.nolRecStatus ='A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("nolRetry", retry);
        return notificationLogsDao.getByQuery(sql, param);
    }

    public void updateNotificationLogsStatus(Long nolId, char status) throws Exception {
        String sql = "UPDATE TNotificationLogs o SET o.nolStatus =:status WHERE o.nolId =:nolId ";
        Map<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("nolId", nolId);
        notificationLogsDao.executeUpdate(sql, param);
    }

    public TNotificationLogs newNotificationLogs(EnumChannelType channelType){
        TNotificationLogs tNotificationLogs = new TNotificationLogs();
        tNotificationLogs.setNolType(channelType.name());
        tNotificationLogs.setNolStatus('P');
        tNotificationLogs.setNolRecStatus('A');
        return tNotificationLogs;
    }
}
