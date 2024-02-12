package com.guud.company.library.application.borrow.listener;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.borrow.BorrowService;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.enums.EnumActionType;
import com.guud.company.library.event.AbstractLibraryEvent;
import com.guud.company.library.event.OverBorrowEvent;
import com.guud.company.library.notification.param.EmailParam;
import com.guud.company.library.notification.param.TelegramParam;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class OverBorrowEventListener extends AbstractLibraryEvent implements ApplicationListener<OverBorrowEvent> {

    private final BorrowService borrowService;
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    @Override
    public void onApplicationEvent(OverBorrowEvent event) {
        super.saveNotificationLogs(ApplicationTypeEnum.BOR.name(), EnumActionType.OVR.name(), event.getApplication().getAppID());
    }

    @Override
    public EmailParam emailParam(String obj) {
        EmailParam emailParam = new EmailParam();
        try {
            TBorrowForm borrow = borrowService.findBorrowFormByApplicationId(obj);
            HashMap<String, String> content = new HashMap<>();
            if (Objects.nonNull(borrow)){
                content.put(":appId", String.valueOf(borrow.getTApplication().getAppID()));
                content.put(":borrowDate", Objects.nonNull(borrow.getBorDtBorrow())? df.format(borrow.getBorDtBorrow()): "");
                content.put(":returnDate", Objects.nonNull(borrow.getBorDtReturn())? df.format(borrow.getBorDtReturn()): "");
                content.put(":borTotalQty", String.valueOf(Objects.nonNull(borrow.getBorTotalQty())));
                content.put(":borTotalOwe", String.valueOf(Objects.nonNull(borrow.getBorTotalOwe())));

                //mapping customer info
                Optional<AppUser> optionalCustomer = appUserRepository.findById(borrow.getBorCustomer().getId());
                if (optionalCustomer.isPresent()){
                    AppUser customer = optionalCustomer.get();
                    content.put(":userName", customer.getUsername());
                    // add list of recipients
                    List<String> recipientList = new ArrayList<>();
                    recipientList.add(customer.getEmail());
                    emailParam.setTo(convertListToArrayString(recipientList));
                }
                //mapping staff info
                AppUser optionalStaff = appUserRepository.findAppUserByUsername(borrow.getBorUidCreate());
                if (Objects.nonNull(optionalStaff)){
                    // add list of staff
                    List<String> ccList = new ArrayList<>();
                    ccList.add(optionalStaff.getEmail());
                    emailParam.setCc(convertListToArrayString(ccList));
                }

                emailParam.setContentFields(content);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("emailParam", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailParam;
    }

    @Override
    public TelegramParam telParam(String obj) {
        TelegramParam telegramParam = new TelegramParam();
        try {
            TBorrowForm borrow = borrowService.findBorrowFormByApplicationId(obj);
            HashMap<String, String> content = new HashMap<>();
            if (Objects.nonNull(borrow)){
                content.put(":appId", borrow.getTApplication().getAppID());
                content.put(":borrowDate", Objects.nonNull(borrow.getBorDtBorrow())? df.format(borrow.getBorDtBorrow()): "");
                content.put(":returnDate", Objects.nonNull(borrow.getBorDtReturn())? df.format(borrow.getBorDtReturn()): "");
                content.put(":borTotalQty", String.valueOf(Objects.nonNull(borrow.getBorTotalQty())));
                content.put(":borTotalOwe", String.valueOf(Objects.nonNull(borrow.getBorTotalOwe())));
                AppUser optionalStaff = appUserRepository.findAppUserByUsername(borrow.getBorUidCreate());
                if (Objects.nonNull(optionalStaff)){
                    content.put(":userName", optionalStaff.getUsername());
                    telegramParam.setChatId(optionalStaff.getChatId());
                }
                telegramParam.setContentFields(content);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("telParam", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telegramParam;
    }
}
