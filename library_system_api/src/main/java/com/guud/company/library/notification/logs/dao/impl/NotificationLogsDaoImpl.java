package com.guud.company.library.notification.logs.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.notification.logs.dao.NotificationLogsDao;
import com.guud.company.library.notification.logs.model.TNotificationLogs;
import org.springframework.stereotype.Service;

@Service("notificationLogsDao")
public class NotificationLogsDaoImpl extends GenericDaoImpl<TNotificationLogs, Long> implements NotificationLogsDao {
}
