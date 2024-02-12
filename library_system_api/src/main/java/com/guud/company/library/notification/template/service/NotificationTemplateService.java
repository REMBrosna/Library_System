package com.guud.company.library.notification.template.service;

import com.guud.company.library.core.GenericDao;
import com.guud.company.library.enums.EnumChannelType;
import com.guud.company.library.enums.EnumContentType;
import com.guud.company.library.notification.template.model.TNotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationTemplateService {

    @Autowired
    @Qualifier("notificationTemplateDao")
    protected GenericDao<TNotificationTemplate, Long> notificationTemplateDao;

    public TNotificationTemplate newTemplate(EnumContentType templateType, EnumChannelType channelType){
        TNotificationTemplate tNotificationTemplate = new TNotificationTemplate();
        tNotificationTemplate.setNotChannelType(channelType.name());
        tNotificationTemplate.setNotContentType(templateType.name());
        tNotificationTemplate.setNotRecStatus('A');
        return tNotificationTemplate;
    }

    public void create(TNotificationTemplate tNotificationTemplate) throws Exception {
        notificationTemplateDao.saveOrUpdate(tNotificationTemplate);
    }

    public TNotificationTemplate getTNotificationTemplateByTemChannelType(Long id) throws Exception {
        return notificationTemplateDao.find(id);
    }

    public void deleteTemplateById(TNotificationTemplate notificationTemplate) throws Exception {
        notificationTemplateDao.remove(notificationTemplate);
    }
}
