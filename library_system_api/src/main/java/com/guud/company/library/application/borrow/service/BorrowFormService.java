package com.guud.company.library.application.borrow.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.borrow.dto.BorrowForm;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.application.item.dto.Items;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.application.item.service.ItemService;
import com.guud.company.library.application.returns.dto.ReturnForm;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.book.model.TBook;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("borrowFormService")
public class BorrowFormService extends AbstractEntityService<TBorrowForm, String, BorrowForm> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_borrow_form";
    protected static Logger log = Logger.getLogger(BorrowFormService.class);

    public BorrowFormService() {
        super("borrowFormDao", auditTag, TBorrowForm.class.getName(), tableName);
    }


    @Autowired
    @Qualifier("borrowFormDao")
    protected GenericDao<TBorrowForm, String> borrowFormDao;
    @Override
    protected TBorrowForm initEnity(TBorrowForm entity) throws Exception {
        return entity;
    }

    @Override
    public BorrowForm add(BorrowForm dto, AppUser principal) throws Exception {
        try {
            if (this.isRecordExists(dto)) {
                throw new Exception("principal already borrow !");
            }
            return super.add(dto, principal);
        }catch (ExceptionInInitializerError ex) {
            log.error("ReturnApplicationService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    public boolean isRecordExists(BorrowForm dto) throws Exception {
        String sql = "SELECT o FROM TBorrowForm o WHERE o.TApplication.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", dto.getApplication().getAppID());
        List<TBorrowForm> list = borrowFormDao.getByQuery(sql, param);
        return !list.isEmpty();
    }


    @Override
    protected TBorrowForm entityFromDTO(BorrowForm dto) throws Exception {
        log.debug("BorrowApplicationService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TBorrowForm entity = new TBorrowForm();
            entity = dto.toEntity(entity);
            Optional<Application> application = Optional.of(dto.getApplication());
            if (application.isPresent()){
                TApplication tApplication = new TApplication();
                BeanUtils.copyProperties(application.get(), tApplication);
                entity.setTApplication(tApplication);
            }

            return entity;
        } catch (Exception ex) {
            log.error("BorrowApplicationService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected BorrowForm dtoFromEntity(TBorrowForm entity) throws Exception {
        log.debug("ApplicationService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            BorrowForm dto = new BorrowForm(entity);
            entity = dto.toEntity(entity);
            Optional<TApplication> application = Optional.of(entity.getTApplication());
            if (application.isPresent()){
                Application app = new Application();
                BeanUtils.copyProperties(entity.getTApplication(), app);
                dto.setApplication(app);
            }
            BeanUtils.copyProperties(entity,dto);
            return dto;
        } catch (Exception ex) {
            log.error("BorrowApplicationService.dtoFromEntity", ex);
            throw ex;
        }

    }

    @Override
    protected String entityKeyFromDTO(BorrowForm dto) throws Exception {
        log.debug("BorrowApplicationService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getBorId();
        } catch (Exception ex) {
            log.error("BorrowApplicationService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TBorrowForm updateEntity(ACTION attribute, TBorrowForm entity, AppUser principal, Date date) throws Exception {
        log.debug("BorrowApplicationService.updateEntity");

        try {
            if (null == entity)
                throw new Exception("param entity is null");
            if (null == principal)
                throw new Exception("param principal is null");
            if (null == date)
                throw new Exception("param date is null");

            Optional<String> opUserId = Optional.ofNullable(principal.getUsername());
            switch (attribute) {
                case CREATE:
                    entity.setBorId(PediUtility.generateId(ApplicationTypeEnum.BOR.name()));
                    entity.setBorUidCreate(opUserId.orElse("SYS"));
                    entity.setBorDtCreate(date);
                    entity.setBorUidLupd(opUserId.orElse("SYS"));
                    entity.setBorDtLupd(date);
                    entity.setBorRecStatus('A');
                    break;

                case MODIFY:
                    entity.setBorUidLupd(opUserId.orElse("SYS"));
                    entity.setBorDtLupd(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("BorrowApplicationService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TBorrowForm updateEntityStatus(TBorrowForm entity, char status) throws Exception {
        log.debug("BorrowApplicationService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setBorRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("BorrowApplicationService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected BorrowForm preSaveUpdateDTO(TBorrowForm storedEntity, BorrowForm dto) throws Exception {
        log.debug("BorrowApplicationService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setBorUidCreate(storedEntity.getBorUidCreate());
            dto.setBorDtCreate(storedEntity.getBorDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("BorrowApplicationService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(BorrowForm var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(BorrowForm dto, boolean wherePrinted) throws Exception {
        log.debug("BorrowApplicationService.getWhereClause");
        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }
            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getApplication().getAppID())) {
                searchStatement.append(getOperator(wherePrinted) + "o.TApplication.appID LIKE :appId");
                wherePrinted = true;
            }
            if (!StringUtils.isEmpty(dto.getBorCustomer().getUsername())) {
                searchStatement.append(getOperator(wherePrinted) + "o.borCustomer.username LIKE :username");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("BorrowApplicationService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(BorrowForm dto) throws Exception {
        log.debug("BorrowApplicationService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getBorId())) {
                parameters.put("borId", "%" + dto.getBorId() + "%");
            }
            if (!StringUtils.isEmpty(dto.getBorId())) {
                parameters.put("username", "%" + dto.getBorId() + "%");
            }
            if (Character.isAlphabetic(dto.getBorRecStatus())) {
                parameters.put("borRecStatus", dto.getBorRecStatus());
            }

            return parameters;
        } catch (Exception ex) {
            log.error("ApplicationService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected BorrowForm whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            BorrowForm dto = new BorrowForm();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("borId")) {
                    dto.setBorId(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("username")) {
                    dto.setBorId(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("borRecStatus")) {
                    dto.setBorRecStatus(opValue.get().charAt(0));
                }
            }

            log.info("dto: " + dto.toString());
            return dto;
        } catch (Exception ex) {
            log.error("whereDto", ex);
            throw ex;
        }
    }

    @Override
    public BorrowForm findById(String id) throws Exception {
        log.debug("ApplicationService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TBorrowForm entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("ApplicationService.findById", ex);
            throw ex;
        }
    }

    @Override
    public BorrowForm deleteById(String id, AppUser principal) throws Exception {
        log.debug("ApplicationService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TBorrowForm entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            BorrowForm dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("ApplicationService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<BorrowForm> filterBy(EntityFilterRequest var1) throws Exception {
        return null;
    }


    @Override
    public BorrowForm findML(BorrowForm dto) throws Exception {
        return null;
    }
}
