package com.guud.company.library.application.borrow.scheduler;

import com.guud.company.library.application.borrow.BorrowService;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.enums.ApplicationStatusEnum;
import com.guud.company.library.event.OverBorrowEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class BorrowScheduler {

    private final BorrowService borrowService;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void doJob() {
        try {
            List<TBorrowForm> borrowList = borrowService.findOverBorrowList(new Date());
            for (TBorrowForm tBorrow: borrowList) {
                borrowService.updateBorrowStatus(tBorrow.getTApplication().getAppID(), ApplicationStatusEnum.OVR.name());
                // public over return date
                OverBorrowEvent event = new OverBorrowEvent(this);
                event.setApplication(tBorrow.getTApplication());
                eventPublisher.publishEvent(event);
            }
        } catch (Exception ex) {
            log.error("Error cron job update over borrow", ex);
        }
    }
}
