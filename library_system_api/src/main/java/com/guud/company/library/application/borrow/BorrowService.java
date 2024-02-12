package com.guud.company.library.application.borrow;

import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.apps.service.ApplicationService;
import com.guud.company.library.application.borrow.dto.BorrowForm;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.application.borrow.service.BorrowFormService;
import com.guud.company.library.application.common.AbstractApplicationService;
import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.audit.dto.AuditLog;
import com.guud.company.library.audit.model.TAuditLog;
import com.guud.company.library.audit.service.AuditLogService;
import com.guud.company.library.configuration.principal.AccountPrincipalService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.IEntityService;
import com.guud.company.library.enums.ApplicationStatusEnum;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.master.book.model.TBook;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class BorrowService extends AbstractApplicationService<BorrowForm> {

    private static Logger log = Logger.getLogger(BorrowService.class);
    @Autowired
    @Qualifier("borrowFormDao")
    protected GenericDao<TBorrowForm, String> borrowFormDao;

    @Autowired
    @Qualifier("returnFormDao")
    protected GenericDao<TReturnForm, String> returnFormDao;



    @Autowired
    @Qualifier("borrowFormService")
    private IEntityService<TBorrowForm, String, BorrowForm> borrowFormService;

    @Autowired
    @Qualifier("applicationService")
    private IEntityService<TApplication, String, Application> applicationService;

    @Autowired
    @Qualifier("auditLogService")
    private IEntityService<TAuditLog, String, AuditLog> auditLogService;

    @Autowired
    private AccountPrincipalService principalService;


    @Override
    protected BorrowForm newApp(String parentId) throws Exception {
        BorrowForm borrowForm = new BorrowForm();
        AppUser appUser = new AppUser();
        borrowForm.setBorCustomer(appUser);
        borrowForm.setApplication(initApplicationByType(ApplicationTypeEnum.BOR.name()));
        return borrowForm;
    }

    @Override
    protected BorrowForm createApp(BorrowForm dto) throws Exception {
        AppUser accountPrincipal = principalService.getAccountPrincipal();
        if (Objects.isNull(accountPrincipal)) {
            throw new EntityNotFoundException(" AccountPrincipal is null");
        }
        Application application = applicationService.add(dto.getApplication(), accountPrincipal);
        dto.setApplication(application);
        dto.setBorTotalOwe(dto.getBorTotalQty());
        AuditLog audit = new AuditLog();
        audit.setAudtAccnid(dto.getApplication().getAppID());
        audit.setAudtUid(dto.getApplication().getAppID());
        audit.setAudtUname(dto.getApplication().getAppUidCreate());
        audit.setAudtEvent(dto.getApplication().getMstApplicationType().getAptCode());
        audit.setAudtReckey(dto.getApplication().getAppID());
        audit.setAudtTimestamp(dto.getApplication().getAppDtConfirm());

        auditLogService.add(audit, accountPrincipal);
        BorrowForm form = borrowFormService.add(dto, accountPrincipal);
        return form;
    }

    @Override
    protected BorrowForm getApp(String key) throws Exception {
        try {
            if (key.isEmpty()) {
                throw new Exception("key param null");
            }
            TBorrowForm tBorrowForm = findBorrowFormByApplicationId(key);
            if (null == tBorrowForm) {
                throw new EntityNotFoundException("entity not found");
            }
            BorrowForm borrowForm = new BorrowForm(tBorrowForm);
            borrowForm.setApplication(applicationService.findById(tBorrowForm.getTApplication().getAppID()));

            return borrowForm;
        } catch (Exception ex) {
            log.error("error getApp ", ex);
            throw new Exception(ex);
        }
    }

    @Override
    protected BorrowForm saveUpdateData(BorrowForm dto) throws Exception {
        return dto;
    }

    @Override
    protected BorrowForm deleteData(BorrowForm dto) throws Exception {
        return null;
    }

    @Override
    protected BorrowForm confirmApp(BorrowForm dto) throws Exception {
        Application application = applicationService.findById(dto.getApplication().getAppID());
        TBorrowForm tBorrowForm = borrowFormDao.find(dto.getBorId());
        if(Objects.nonNull(tBorrowForm)){
            tBorrowForm.setBorDtBorrow(new Date());
            borrowFormDao.saveOrUpdate(tBorrowForm);
        }
        AppUser accountPrincipal = principalService.getAccountPrincipal();
        if(null != application){
            MstApplicationStatus mstApplicationStatus = application.getMstApplicationStatus();
            mstApplicationStatus.setApsCode(ApplicationStatusEnum.BOR.name());
            application.setAppDtConfirm(new Date());
            application.setMstApplicationStatus(mstApplicationStatus);
            applicationService.update(application,accountPrincipal);
        }
        return dto;
    }

    public BorrowForm dtoFromEntity(TBorrowForm entity){
        BorrowForm dto = new BorrowForm();

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
            dto.setApplication(application);
        }
        Optional<AppUser> optionalCustomer = Optional.ofNullable(entity.getBorCustomer());
        if (optionalCustomer.isPresent()){
            AppUser customer = new AppUser();
            customer.setId(optionalCustomer.get().getId());
            customer.setEmail(optionalCustomer.get().getEmail());
            customer.setUsername(optionalCustomer.get().getUsername());
            dto.setBorCustomer(customer);
        }
        return dto;
    }

    public TBorrowForm findBorrowFormByApplicationId(String appId) throws Exception {
        String sql = "SELECT o FROM TBorrowForm o WHERE o.TApplication.appID =:appId AND o.borRecStatus = 'A' ";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        List<TBorrowForm> list = borrowFormDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    public TReturnForm findReturnFormByApplicationId(String appId) throws Exception {
        String sql = "SELECT o FROM TReturnForm o WHERE o.borApplication.appID =:appId AND o.TApplication.TMstApplicationStatus.apsCode = 'DRF'";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        List<TReturnForm> list = returnFormDao.getByQuery(sql, param);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
    public List<TReturnForm> findAllReturn() throws Exception {
        String sql = "SELECT o FROM TReturnForm o WHERE o.recStatus = 'A' ";
        return returnFormDao.getByQuery(sql);
    }

    public List<TBorrowForm> findOverBorrowList(Date currentDate) throws Exception {
        String sql = "SELECT o FROM TBorrowForm o WHERE o.borDtReturn <:currentDate AND o.borRecStatus = 'A' AND o.TApplication.TMstApplicationStatus.apsCode not in ('DRF', 'OVR', 'RET') ";
        Map<String, Object> param = new HashMap<>();
        param.put("currentDate", currentDate);
        return borrowFormDao.getByQuery(sql, param);
    }

    public void updateBorrowStatus(String appId, String status) throws Exception {
        String sql = "UPDATE TApplication o SET o.TMstApplicationStatus.apsCode =:status WHERE o.appID =:appId ";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        param.put("status", status);
        borrowFormDao.executeUpdate(sql, param);
    }
}
