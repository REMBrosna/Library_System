package com.guud.company.library.application.apps.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("applicationService")
public class ApplicationService extends AbstractEntityService<TApplication, String, Application> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_application";
    protected static Logger log = Logger.getLogger(ApplicationService.class);

    public ApplicationService() {
        super("applicationDao", auditTag, TApplication.class.getName(), tableName);
    }
    @Override
    protected TApplication initEnity(TApplication entity) throws Exception {
        return entity;
    }
    @Autowired
    @Qualifier("applicationDao")
    protected GenericDao<TApplication, String> applicationDao;

    public void updateApplicationStatusByBorID(String applicationId, String status) throws Exception {
        String sql = "UPDATE TApplication o SET o.TMstApplicationStatus.apsCode =:status WHERE o.appID =:appId";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", applicationId);
        param.put("status", status);
        applicationDao.executeUpdate(sql, param);
    }

    @Override
    protected TApplication entityFromDTO(Application dto) throws Exception {
        log.debug("ApplicationService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TApplication entity = new TApplication();
            entity = dto.toEntity(entity);


            TMstApplicationType applicationType = new TMstApplicationType();
            TMstApplicationStatus applicationStatus = new TMstApplicationStatus();

            dto.getMstApplicationType().toEntity(applicationType);
            entity.setTMstApplicationType(applicationType);

            dto.getMstApplicationStatus().toEntity(applicationStatus);
            entity.setTMstApplicationStatus(applicationStatus);

            return entity;
        } catch (Exception ex) {
            log.error("ApplicationService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected Application dtoFromEntity(TApplication entity) throws Exception {
        log.debug("ApplicationService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            Application dto = new Application();


            MstApplicationType applicationType = new MstApplicationType();
            MstApplicationStatus applicationStatus = new MstApplicationStatus();

            BeanUtils.copyProperties(entity.getTMstApplicationType(), applicationType);
            BeanUtils.copyProperties(entity.getTMstApplicationStatus(),applicationStatus);

            dto.setMstApplicationType(applicationType);
            dto.setMstApplicationStatus(applicationStatus);

            BeanUtils.copyProperties(entity,dto);
            return (dto);
        } catch (Exception ex) {
            log.error("ApplicationService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(Application dto) throws Exception {
        log.debug("ApplicationService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getAppID();
        } catch (Exception ex) {
            log.error("ApplicationService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TApplication updateEntity(ACTION attribute, TApplication entity, AppUser principal, Date date) throws Exception {
        log.debug("ApplicationService.updateEntity");

        try {
            if (null == entity)
                throw new Exception("param entity is null");
            if (null == principal)
                throw new Exception("param principal is null");
            if (null == date)
                throw new Exception("param date is null");

            Optional<String> opUserId = Optional.ofNullable(principal.getUsername());
            String appCode = entity.getTMstApplicationType().getAptCode();
            switch (attribute) {
                case CREATE:
                    entity.setAppID(PediUtility.generateId(appCode));
                    entity.setAppUidCreate(opUserId.orElse("SYS"));
                    entity.setAppDtCreate(date);
                    entity.setAppUidLupd(opUserId.orElse("SYS"));
                    entity.setAppDtLupd(date);
                    entity.setAppRecStatus('A');
                    break;

                case MODIFY:
                    entity.setAppUidLupd(opUserId.orElse("SYS"));
                    entity.setAppDtLupd(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("ApplicationService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TApplication updateEntityStatus(TApplication entity, char status) throws Exception {
        log.debug("ApplicationService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setAppRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("ApplicationService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected Application preSaveUpdateDTO(TApplication storedEntity, Application dto) throws Exception {
        log.debug("ApplicationService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setAppUidCreate(storedEntity.getAppUidCreate());
            dto.setAppDtCreate(storedEntity.getAppDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("ApplicationService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(Application var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(Application dto, boolean wherePrinted) throws Exception {
        log.debug("ApplicationService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getAppID())) {
                searchStatement.append(getOperator(wherePrinted) + "o.appID LIKE :appID");
                wherePrinted = true;
            }
            if (Character.isAlphabetic(dto.getAppRecStatus())) {
                searchStatement.append(getOperator(wherePrinted) + "o.appRecStatus = :appRecStatus");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("ApplicationService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(Application dto) throws Exception {
        log.debug("ApplicationService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getAppID())) {
                parameters.put("appID", "%" + dto.getAppID() + "%");
            }
            if (Character.isAlphabetic(dto.getAppRecStatus())) {
                parameters.put("appRecStatus", dto.getAppRecStatus());
            }

            return parameters;
        } catch (Exception ex) {
            log.error("ApplicationService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected Application whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Application dto = new Application();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("appID")) {
                    dto.setAppID(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("appRecStatus")) {
                    dto.setAppRecStatus(opValue.get().charAt(0));
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
    public Application findById(String id) throws Exception {
        log.debug("ApplicationService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TApplication entity = dao.find(id);
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
    public Application deleteById(String id, AppUser principal) throws Exception {
        log.debug("ApplicationService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TApplication entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            Application dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("ApplicationService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<Application> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Application dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TMstProvince o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TApplication> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
                    filterRequest.getDisplayLength(), filterRequest.getDisplayStart());

            return entities.stream().map(x -> {
                try {
                    return dtoFromEntity(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("filterBy", ex);
            throw ex;
        }
    }

    public Optional<Object> getApplicationList(Map<String, String> params) throws Exception {
        log.debug("getApplicationList");
        try {
            if (io.jsonwebtoken.lang.Collections.isEmpty(params)) {
                throw new Exception("param params null or empty");
            }

            EntityFilterRequest filterRequest = new EntityFilterRequest();
            // start and length parameter extraction
            filterRequest.setDisplayStart(
                    params.containsKey("iDisplayStart") ? Integer.parseInt(params.get("iDisplayStart")) : -1);
            filterRequest.setDisplayLength(
                    params.containsKey("iDisplayLength") ? Integer.parseInt(params.get("iDisplayLength"))
                            : -1);
            // where parameters extraction
            ArrayList<EntityWhere> whereList = new ArrayList<>();
            List<String> searches = params.keySet().stream().filter(x -> x.contains("sSearch_"))
                    .collect(Collectors.toList());
            for (int nIndex = 1; nIndex <= searches.size(); nIndex++) {
                String searchParam = params.get("sSearch_" + nIndex);
                String valueParam = params.get("mDataProp_" + nIndex);
                log.info("searchParam: " + searchParam + " valueParam: " + valueParam);
                whereList.add(new EntityWhere(valueParam, searchParam));
            }
            filterRequest.setWhereList(whereList);
            // order by parameters extraction
            Optional<String> opSortAttribute = Optional.ofNullable(params.get("mDataProp_0"));
            Optional<String> opSortOrder = Optional.ofNullable(params.get("sSortDir_0"));
            if (opSortAttribute.isPresent() && opSortOrder.isPresent()) {
                EntityOrderBy orderBy = new EntityOrderBy();
                orderBy.setAttribute(opSortAttribute.get());
                orderBy.setOrdered(opSortOrder.get().equalsIgnoreCase("desc") ? EntityOrderBy.ORDERED.DESC : EntityOrderBy.ORDERED.ASC);
                filterRequest.setOrderBy(orderBy);
            }
            if (!filterRequest.isValid()) {
                throw new Exception("Invalid request: " + filterRequest.toString());
            }

            List<Application> en = this.filterBy(filterRequest);

            @SuppressWarnings("unchecked")
            List<Object> entities = List.class.cast(en);
            EntityFilterResponse filterResponse = new EntityFilterResponse();
            filterResponse.setiTotalRecords(entities.size());
            filterResponse.setiTotalDisplayRecords(filterRequest.getTotalRecords());
            filterResponse.setAaData((ArrayList<Object>) entities);

            return Optional.of(filterResponse);
        } catch (Exception ex) {
            log.error("getPaymentAdvices", ex);
            throw ex;
        }

    }

    @Override
    public Application findML(Application dto) throws Exception {
        return null;
    }
}
