package com.guud.company.library.application.returns.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.returns.dto.ReturnForm;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("returnFormService")
public class ReturnFormService extends AbstractEntityService<TReturnForm, String, ReturnForm> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_return_form";
    protected static Logger log = Logger.getLogger(ReturnFormService.class);

    public ReturnFormService() {
        super("returnFormDao", auditTag, TReturnForm.class.getName(), tableName);
    }
    @Autowired
    @Qualifier("returnFormDao")
    protected GenericDao<TReturnForm, String> returnFormDao;

    @Override
    protected TReturnForm initEnity(TReturnForm entity) throws Exception {
        return entity;
    }

    @Override
    public ReturnForm add(ReturnForm dto, AppUser principal) throws Exception {
        return super.add(dto, principal);
    }

    @Override
    protected TReturnForm entityFromDTO(ReturnForm dto) throws Exception {
        log.debug("ReturnApplicationService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TReturnForm entity = new TReturnForm();
            entity = dto.toEntity(entity);

            Optional<Application> application = Optional.of(dto.getApplication());
            if (application.isPresent()){
                TApplication tApplication = new TApplication();
                BeanUtils.copyProperties(application.get(), tApplication);
                entity.setTApplication(tApplication);
            }

            Optional<Application> borApplication = Optional.of(dto.getBorApplication());
            if (borApplication.isPresent()){
                TApplication tApplication = new TApplication();
                BeanUtils.copyProperties(borApplication.get(), tApplication);
                entity.setBorApplication(tApplication);
            }


            return entity;
        } catch (Exception ex) {
            log.error("ReturnApplicationService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected ReturnForm dtoFromEntity(TReturnForm entity) throws Exception {
        log.debug("ReturnApplicationService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            ReturnForm dto = new ReturnForm(entity);
            entity = dto.toEntity(entity);

            Optional<TApplication> application = Optional.of(entity.getTApplication());
            if (application.isPresent()){
                Application app = new Application();
                BeanUtils.copyProperties(entity.getTApplication(), app);
                dto.setApplication(app);
            }

            Optional<TApplication> borApplication = Optional.of(entity.getBorApplication());
            if (borApplication.isPresent()){
                Application app = new Application();
                BeanUtils.copyProperties(borApplication.get(), app);
                dto.setBorApplication(app);
            }

            return dto;
        } catch (Exception ex) {
            log.error("ReturnApplicationService.dtoFromEntity", ex);
            throw ex;
        }

    }

    @Override
    protected String entityKeyFromDTO(ReturnForm dto) throws Exception {
        log.debug("ReturnApplicationService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }
            return dto.getRetId();
        } catch (Exception ex) {
            log.error("ReturnApplicationService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TReturnForm updateEntity(ACTION attribute, TReturnForm entity, AppUser principal, Date date) throws Exception {
        log.debug("ReturnApplicationService.updateEntity");

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
                    entity.setRetId(PediUtility.generateId(ApplicationTypeEnum.RET.name()));
                    entity.setRetUidCreate(opUserId.orElse("SYS"));
                    entity.setRetDtCreate(date);
                    entity.setRetUidLupd(opUserId.orElse("SYS"));
                    entity.setRetDtLupd(date);
                    entity.setRecStatus('A');
                    break;

                case MODIFY:
                    entity.setRetUidLupd(opUserId.orElse("SYS"));
                    entity.setRetDtLupd(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("ReturnApplicationService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TReturnForm updateEntityStatus(TReturnForm entity, char status) throws Exception {
        log.debug("ReturnApplicationService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("ReturnApplicationService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected ReturnForm preSaveUpdateDTO(TReturnForm storedEntity, ReturnForm dto) throws Exception {
        log.debug("ReturnApplicationService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setRetUidCreate(storedEntity.getRetUidCreate());
            dto.setRetDtCreate(storedEntity.getRetDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("ReturnApplicationService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(ReturnForm var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(ReturnForm dto, boolean wherePrinted) throws Exception {
        log.debug("ReturnApplicationService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getRetId())) {
                searchStatement.append(getOperator(wherePrinted) + "o.retId LIKE :retId");
                wherePrinted = true;
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                searchStatement.append(getOperator(wherePrinted) + "o.recStatus = :recStatus");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("ReturnApplicationService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(ReturnForm dto) throws Exception {
        log.debug("ReturnApplicationService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getRetId())) {
                parameters.put("retId", "%" + dto.getRetId() + "%");
            }
            if (Character.isAlphabetic(dto.getRecStatus())) {
                parameters.put("borRecStatus", dto.getRecStatus());
            }

            return parameters;
        } catch (Exception ex) {
            log.error("ApplicationService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected ReturnForm whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            ReturnForm dto = new ReturnForm();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("retId")) {
                    dto.setRetId(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("recStatus")) {
                    dto.setRecStatus(opValue.get().charAt(0));
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
    public ReturnForm findById(String id) throws Exception {
        log.debug("ApplicationService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TReturnForm entity = dao.find(id);
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
    public ReturnForm deleteById(String id, AppUser principal) throws Exception {
        log.debug("ApplicationService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TReturnForm entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            ReturnForm dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("ApplicationService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<ReturnForm> filterBy(EntityFilterRequest var1) throws Exception {
        return null;
    }


    @Override
    public ReturnForm findML(ReturnForm dto) throws Exception {
        return null;
    }
}
