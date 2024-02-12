package com.guud.company.library.application.returns;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.apps.service.ApplicationService;
import com.guud.company.library.application.borrow.BorrowService;
import com.guud.company.library.application.borrow.dto.BorrowForm;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.application.borrow.service.BorrowFormService;
import com.guud.company.library.application.common.AbstractApplicationService;
import com.guud.company.library.application.item.dto.Items;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.application.item.service.ItemService;
import com.guud.company.library.application.returns.dto.ReturnForm;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.audit.dto.AuditLog;
import com.guud.company.library.audit.model.TAuditLog;
import com.guud.company.library.configuration.principal.AccountPrincipalService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.IEntityService;
import com.guud.company.library.enums.ApplicationStatusEnum;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.master.book.dto.Book;
import com.guud.company.library.master.book.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service("")
public class ReturnService extends AbstractApplicationService<ReturnForm> {

    private static Logger log = Logger.getLogger(ReturnService.class);
    @Autowired
    @Qualifier("returnFormDao")
    protected GenericDao<TReturnForm, String> returnFormDao;

    @Autowired
    @Qualifier("ItemDao")
    protected GenericDao<TItems, String> ItemDao;

    @Autowired
    @Qualifier("returnFormService")
    private IEntityService<TReturnForm, String, ReturnForm> returnFormService;

    @Autowired
    @Qualifier("applicationService")
    private IEntityService<TApplication, String, Application> applicationService;

    @Autowired
    @Qualifier("auditLogService")
    private IEntityService<TAuditLog, String, AuditLog> auditLogService;

    @Autowired
    private AccountPrincipalService principalService;
    @Autowired
    private ApplicationService applicationServiceUpdateStatus;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private ItemService itemService;


    public void updateBookReturn(String bokId, int bokQty) throws Exception {
        String sql = "UPDATE TBook o SET o.bokQty =:bokQty  WHERE o.bokId=:bokId";
        Map<String, Object> param = new HashMap<>();
        param.put("bokId", bokId);
        param.put("bokQty", bokQty);
        returnFormDao.executeUpdate(sql, param);
    }
    @Override
    protected ReturnForm newApp(String parentId) throws Exception {
        ReturnForm returnForm = new ReturnForm();
        returnForm.setApplication(initApplicationByType(ApplicationTypeEnum.RET.name()));
        returnForm.setBorApplication(applicationService.findById(parentId));
        return returnForm;
    }

    @Override
    protected ReturnForm createApp(ReturnForm dto) throws Exception {
        AppUser accountPrincipal = principalService.getAccountPrincipal();
        if (Objects.isNull(accountPrincipal)) {
            throw new EntityNotFoundException(" AccountPrincipal is null");
        }
        Application application = applicationService.add(dto.getApplication(), accountPrincipal);
        dto.setApplication(application);
        dto.setReturnQty(dto.getReturnQty());
        AuditLog audit = new AuditLog();

        audit.setAudtEvent(dto.getApplication().getMstApplicationType().getAptCode());
        audit.setAudtAccnid(dto.getApplication().getAppID());
        audit.setAudtUid(dto.getApplication().getAppID());
        audit.setAudtUname(dto.getApplication().getAppUidCreate());
        audit.setAudtReckey(dto.getApplication().getAppID());
        audit.setAudtTimestamp(dto.getApplication().getAppDtConfirm());

        auditLogService.add(audit, accountPrincipal);
        ReturnForm form = returnFormService.add(dto, accountPrincipal);
        return form;
    }

    @Override
    protected ReturnForm getApp(String key) throws Exception {
        try {
            if (key.isEmpty()) {
                throw new Exception("key param null");
            }
            TReturnForm tReturnForm= findReturnFormByApplicationId(key);
            if (null == tReturnForm) {
                throw new EntityNotFoundException("entity not found");
            }
            ReturnForm returnForm = dtoFromEntity(tReturnForm);

            return returnForm;
        } catch (Exception ex) {
            log.error("error getApp ", ex);
            throw new Exception(ex);
        }
    }
    @Override
    protected ReturnForm saveUpdateData(ReturnForm dto) throws Exception {
        return dto;
    }

    @Override
    protected ReturnForm deleteData(ReturnForm dto) throws Exception {
        return null;
    }

    @Override
    protected ReturnForm confirmApp(ReturnForm dto) throws Exception {
        Application application = applicationService.findById(dto.getApplication().getAppID());
        TBorrowForm findBorrowId = borrowService.findBorrowFormByApplicationId(dto.getBorApplication().getAppID());
        AppUser accountPrincipal = principalService.getAccountPrincipal();
        if(application != null){
            MstApplicationStatus mstApplicationStatus = application.getMstApplicationStatus();
            mstApplicationStatus.setApsCode(ApplicationStatusEnum.RET.name());
            application.setMstApplicationStatus(mstApplicationStatus);
            application.setAppDtConfirm(new Date());
            applicationService.update(application,accountPrincipal);
            if(findBorrowId.getBorTotalOwe() == 0){
                applicationServiceUpdateStatus.updateApplicationStatusByBorID(findBorrowId.getTApplication().getAppID(), ApplicationStatusEnum.RET.name());
            }else if(findBorrowId.getBorTotalOwe() > 0){
                applicationServiceUpdateStatus.updateApplicationStatusByBorID(findBorrowId.getTApplication().getAppID(), ApplicationStatusEnum.OWE.name());
            }
            List<TItems> itemsList = itemService.listItems(findBorrowId.getTApplication().getAppID());
            for (TItems items : itemsList) {
                updateBookReturn(items.getBook().getBokId(), items.getBook().getBokQty() + dto.getReturnQty());
            }
        }
        return dto;
    }
    public ReturnForm dtoFromEntity(TReturnForm entity){
        ReturnForm dto = new ReturnForm();
        BeanUtils.copyProperties(entity,dto);
        Optional<TApplication> optionalTApplication = Optional.ofNullable(entity.getTApplication());
        if (optionalTApplication.isPresent()){
            Application application = new Application();
            BeanUtils.copyProperties(optionalTApplication.get(), application);

            Application borApplication = new Application();
            BeanUtils.copyProperties(optionalTApplication.get(), borApplication);

            Optional<TMstApplicationStatus> optionalTMstApplicationStatus = Optional.of(optionalTApplication.get().getTMstApplicationStatus());
            if (optionalTMstApplicationStatus.isPresent()){
                MstApplicationStatus applicationStatus = new MstApplicationStatus();
                BeanUtils.copyProperties(optionalTMstApplicationStatus.get(), applicationStatus);
                application.setMstApplicationStatus(applicationStatus);
                borApplication.setMstApplicationStatus(applicationStatus);
            }
            Optional<TMstApplicationType> optionalTMstApplicationType = Optional.of(optionalTApplication.get().getTMstApplicationType());
            if(optionalTMstApplicationType.isPresent()){
                MstApplicationType applicationType = new MstApplicationType();
                BeanUtils.copyProperties(optionalTMstApplicationType.get(), applicationType);
                application.setMstApplicationType(applicationType);
                borApplication.setMstApplicationType(applicationType);
            }
            if(null != entity.getBorApplication()){
                BeanUtils.copyProperties(entity.getBorApplication(), borApplication);
            }
            dto.setApplication(application);
            dto.setBorApplication(borApplication);
        }
        return dto;
    }

    public TReturnForm findReturnFormByApplicationId(String appId) throws Exception {
        String sql = "SELECT o FROM TReturnForm o WHERE o.TApplication.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        List<TReturnForm> list = returnFormDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
