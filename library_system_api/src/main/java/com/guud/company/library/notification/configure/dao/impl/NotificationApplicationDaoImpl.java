package com.guud.company.library.notification.configure.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.notification.configure.dao.NotificationApplicationDao;
import com.guud.company.library.notification.configure.model.TNotificationApplication;
import org.springframework.stereotype.Service;

@Service("notificationApplicationDao")
public class NotificationApplicationDaoImpl extends GenericDaoImpl<TNotificationApplication, Long> implements NotificationApplicationDao {
}
