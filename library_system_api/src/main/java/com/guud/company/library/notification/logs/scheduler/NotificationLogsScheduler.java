package com.guud.company.library.notification.logs.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guud.company.library.enums.EnumChannelType;
import com.guud.company.library.notification.email.service.EmailServiceImpl;
import com.guud.company.library.notification.logs.model.TNotificationLogs;
import com.guud.company.library.notification.logs.service.NotificationLogsService;
import com.guud.company.library.notification.param.EmailParam;
import com.guud.company.library.notification.param.TelegramParam;
import com.guud.company.library.notification.telegram.service.TelegramServiceImpl;
import com.guud.company.library.sysparam.service.SystemParamService;
import com.guud.company.library.utils.LibraryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class NotificationLogsScheduler {

    @Autowired
    private NotificationLogsService notificationLogsService;
    @Autowired
    private SystemParamService systemParamService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private TelegramServiceImpl telegramService;

    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void doJob() throws Exception {
        String systemParam = systemParamService.getValue(LibraryConstants.NOTIFICATION_RETRY_KEY, "3");
        ObjectMapper objectMapper = new ObjectMapper();
        List<TNotificationLogs> notificationLogsList = notificationLogsService.getAllPendingAndFailedNotification(Integer.parseInt(systemParam));
        for (TNotificationLogs notificationLogs: notificationLogsList) {
            try {
                if (EnumChannelType.EMAIL.name().equals(notificationLogs.getNolType())){
                    EmailParam param = objectMapper.readValue(notificationLogs.getNolBody(), EmailParam.class);
                    emailService.notify(param);
                } else if (EnumChannelType.TELEGRAM.name().equals(notificationLogs.getNolType())){
                    TelegramParam param = objectMapper.readValue(notificationLogs.getNolBody(), TelegramParam.class);
                    telegramService.notify(param);
                }
                notificationLogs.setNolStatus('S');
            } catch (Exception ex) {
                log.error("Error cron job update over borrow", ex);
                int retry = notificationLogs.getNolRetry();
                notificationLogs.setNolRetry(++retry);
                notificationLogs.setNolStatus('F');
            }
            notificationLogsService.updateNotificationLogsStatus(notificationLogs.getNolId(), notificationLogs.getNolStatus());
        }

    }
}
