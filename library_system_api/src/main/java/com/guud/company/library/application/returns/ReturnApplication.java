package com.guud.company.library.application.returns;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.common.AbstractApplication;
import com.guud.company.library.application.returns.dto.ReturnForm;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("returnApplication")
public class ReturnApplication extends AbstractApplication<ReturnForm, TReturnForm, ReturnService> {

    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(ReturnApplication.class);

    @Autowired
    @Qualifier("returnFormDao")
    protected GenericDao<TReturnForm, String> returnFormDao;

    @Override
    public ReturnForm newApp(String parentId) throws Exception {
        return service.newApp(parentId);
    }


    @Override
    public ReturnForm createApp(ReturnForm dto) throws Exception {
        return service.createApp(dto);
    }

    @Override
    public ReturnForm updateApp(ReturnForm dto) throws Exception {
        return service.saveUpdateData(dto);
    }

    @Override
    protected ReturnForm getAppForm(String key) throws Exception {
        return service.getApp(key);
    }

    @Override
    public ReturnForm delete(ReturnForm dto) throws Exception {
        return service.deleteData(dto);
    }

    @Override
    public ReturnForm confirmApp(ReturnForm dto) throws Exception {
        return service.confirmApp(dto);
    }

    @Override
    protected TReturnForm initEntity(TReturnForm entity) throws Exception {
        Hibernate.initialize(entity.getTApplication());
        Hibernate.initialize(entity.getTApplication());
        return entity;
    }

    @Override
    public List<ReturnForm> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest) {
                throw new Exception("param filterRequest null");
            }
            ReturnForm dto = this.whereDto(filterRequest);
            if (null == dto) {
                throw new Exception("whereDto null");
            }
            filterRequest.setTotalRecords(this.countByAnd(dto));
            String selectClause = "FROM TReturnForm o ";
            String orderByClause = formatOrderBy(filterRequest.getOrderBy().toString());
            List<TReturnForm> entities = this.findEntitiesByAnd(dto, selectClause, orderByClause, filterRequest.getDisplayLength(), filterRequest.getDisplayStart());
            return entities.stream().map(x -> {
                try {
                    return service.dtoFromEntity(x);
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

    @Override
    public int countByAnd(ReturnForm dto) throws Exception {
        try {
            if (null == dto){
                throw new Exception("param dto null");
            }
            int count;
            String sql = "SELECT COUNT(o) FROM TReturnForm o ";
            String whereClause = this.getWhereClause(dto, false);
            HashMap<String, Object> parameters = (HashMap<String, Object>) this.getParameters(dto);
            if (StringUtils.isNotEmpty(whereClause) && null != parameters && parameters.size() > 0) {
                count = returnFormDao.count(sql.concat(whereClause), parameters);
            } else {
                count = returnFormDao.count(sql);
            }
            return count;
        } catch (Exception ex) {
            log.error("countByAnd", ex);
            throw ex;
        }
    }

    @Override
    public List<TReturnForm> findEntitiesByAnd(ReturnForm dto, String selectClause, String orderByClause, int limit, int offset) throws Exception {
        try {
            if (null == dto){
                throw new Exception("param dto null");
            }
            if (StringUtils.isEmpty(selectClause)) {
                throw new Exception("param selectClause null or empty");
            }
            if (StringUtils.isEmpty(orderByClause)) {
                throw new Exception("param orderByClause null or empty");
            }
            String whereClause = this.getWhereClause(dto, false);
            HashMap<String, Object> parameters = (HashMap<String, Object>) this.getParameters(dto);
            String hqlQuery = StringUtils.isEmpty(whereClause) ? selectClause + orderByClause : selectClause + whereClause + orderByClause;
            List<TReturnForm> entities = returnFormDao.getByQuery(hqlQuery, parameters, limit, offset);
            for (TReturnForm entity : entities) {
                this.initEntity(entity);
            }
            return entities;
        } catch (Exception ex) {
            log.error("findEntitiesByAnd", ex);
            throw ex;
        }
    }

    @Override
    protected ReturnForm whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest) {
                throw new Exception("param filterRequest null");
            }
            ReturnForm dto = new ReturnForm();
            Application application = new Application();
            Application borApplication = new Application();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent()) {
                    continue;
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("application.appID")) {
                    application.setAppID(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("borApplication.appID")) {
                    borApplication.setAppID(opValue.get());
                }
            }
            dto.setApplication(application);
            dto.setBorApplication(borApplication);
            return dto;
        } catch (Exception ex) {
            log.error("whereDto", ex);
            throw ex;
        }
    }

    @Override
    protected String getWhereClause(ReturnForm dto, boolean wherePrinted) throws Exception {
        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }
            StringBuilder searchStatement = new StringBuilder();
            searchStatement.append(getOperator(wherePrinted)).append("o.TApplication.appRecStatus='A' AND o.TApplication.TMstApplicationType.aptCode = 'RET' ");
            wherePrinted = true;
            Optional<Application> opPediApps = Optional.ofNullable(dto.getApplication());
            if (opPediApps.isPresent()) {
                Application pediApp = opPediApps.get();
                if (StringUtils.isNotBlank(pediApp.getAppID())){
                    searchStatement.append(
                            getOperator(wherePrinted) + "o.TApplication.appID LIKE :appID ");
                }
                Optional<Application> appUser = Optional.ofNullable(dto.getBorApplication());
                if(appUser.isPresent()){
                    Application user = appUser.get();
                    if (StringUtils.isNotBlank(user.getAppID())){
                        searchStatement.append(
                                getOperator(wherePrinted) + "o.borApplication.appID LIKE :appId ");
                        wherePrinted = true;
                    }
                }
            }
            return searchStatement.toString();

        } catch (Exception ex) {
            log.error("getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected Map<String, Object> getParameters(ReturnForm dto) throws Exception {
        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }
            HashMap<String, Object> parameters = new HashMap<>();

            Optional<Application> opPediApps = Optional.ofNullable(dto.getApplication());
            if (opPediApps.isPresent()) {
                Application pediApp = opPediApps.get();
                if (!StringUtils.isEmpty(pediApp.getAppID())) {
                    parameters.put("appID", "%" + pediApp.getAppID() + "%");
                }
            }
            Optional<Application> appUser = Optional.ofNullable(dto.getBorApplication());
            if (appUser.isPresent()) {
                Application user = appUser.get();
                if (!StringUtils.isEmpty(user.getAppID())) {
                    parameters.put("appId", "%" + user.getAppID() + "%");
                }
            }
            return parameters;
        } catch (Exception ex) {
            log.error("getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected String formatOrderBy(String orderByClause) throws Exception {
        String newAttr = orderByClause;
        if(StringUtils.contains(newAttr, "application"))
            newAttr = newAttr.replace("application","TApplication");
        return newAttr;
    }
}
