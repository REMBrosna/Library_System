package com.guud.company.library.notification.template.dao.impl;

import com.guud.company.library.core.GenericDaoImpl;
import com.guud.company.library.notification.template.dao.NotificationTemplateDao;
import com.guud.company.library.notification.template.model.TNotificationTemplate;
import org.springframework.stereotype.Service;

@Service("notificationTemplateDao")
public class NotificationTemplateDaoImpl extends GenericDaoImpl<TNotificationTemplate, Long> implements NotificationTemplateDao {
}
