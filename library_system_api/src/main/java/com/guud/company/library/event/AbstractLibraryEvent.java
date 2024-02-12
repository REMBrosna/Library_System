package com.guud.company.library.event;

import com.guud.company.library.administrator.repository.AppUserRepository;
import com.guud.company.library.enums.EnumChannelType;
import com.guud.company.library.notification.configure.model.TNotificationApplication;
import com.guud.company.library.notification.configure.service.NotificationApplicationService;
import com.guud.company.library.notification.logs.model.TNotificationLogs;
import com.guud.company.library.notification.logs.service.NotificationLogsService;
import com.guud.company.library.notification.param.EmailParam;
import com.guud.company.library.notification.param.TelegramParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;

public abstract class AbstractLibraryEvent{

    @Autowired
    private NotificationLogsService notificationLogsService;
    @Autowired
    private NotificationApplicationService notificationApplicationService;
    @Autowired
    protected AppUserRepository appUserRepository;

    protected abstract EmailParam emailParam(String obj);

    protected abstract TelegramParam telParam(String obj);

    protected void saveNotificationLogs(String appType, String actionType, String obj) throws Exception {
        TNotificationApplication notificationApplication = notificationApplicationService.getTNotificationApplicationByAppTypeAndAppAction(appType, actionType);
        if (Objects.nonNull(notificationApplication)){
            if (notificationApplication.getNoaRequiredEmail() =='Y'){
                notificationLogsService.create(covertParamToJson(EnumChannelType.EMAIL, notificationApplication.getNoaEmailTemplateId(), obj));
            }
            if (notificationApplication.getNoaRequiredTelegram() =='Y'){
                notificationLogsService.create(covertParamToJson(EnumChannelType.TELEGRAM, notificationApplication.getNoaTelegramTemplateId(), obj));
            }
        }
    }

    private TNotificationLogs covertParamToJson(EnumChannelType channelType, Long templateId, String object) throws Exception {
        TNotificationLogs obj = notificationLogsService.newNotificationLogs(channelType);
        if (EnumChannelType.EMAIL.equals(channelType)){
            EmailParam emailParam = emailParam(object);
            emailParam.setTemplateId(templateId);
            obj.setNolBody(emailParam.toJson());
        } else if (EnumChannelType.TELEGRAM.equals(channelType)){
            TelegramParam telegramParam = telParam(object);
            telegramParam.setTemplateId(templateId);
            obj.setNolBody(telegramParam.toJson());
        }
        notificationLogsService.create(obj);
        return obj;
    }

    protected String[] convertListToArrayString(List<String> list){
        String[] strArr = new String[list.size()];
        strArr = list.toArray(strArr);
        return strArr;
    }
}
