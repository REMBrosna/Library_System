package com.guud.company.library.application.borrow;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.borrow.dto.BorrowForm;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.application.common.AbstractApplication;
import com.guud.company.library.core.GenericDao;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service("borrowApplication")
public class BorrowApplication extends AbstractApplication<BorrowForm, TBorrowForm, BorrowService> {

    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(BorrowApplication.class);

    @Autowired
    @Qualifier("borrowFormDao")
    protected GenericDao<TBorrowForm, String> borrowFormDao;

    @Override
    public BorrowForm newApp(String parentId) throws Exception {
        return service.newApp(parentId);
    }


    @Override
    public BorrowForm createApp(BorrowForm dto) throws Exception {
        return service.createApp(dto);
    }

    @Override
    public BorrowForm updateApp(BorrowForm dto) throws Exception {
        return service.saveUpdateData(dto);
    }

    @Override
    protected BorrowForm getAppForm(String key) throws Exception {
        return service.getApp(key);
    }

    @Override
    public BorrowForm delete(BorrowForm dto) throws Exception {
        return service.deleteData(dto);
    }

    @Override
    public BorrowForm confirmApp(BorrowForm dto) throws Exception {
        return service.confirmApp(dto);
    }

    @Override
    protected TBorrowForm initEntity(TBorrowForm entity) throws Exception {
        Hibernate.initialize(entity.getTApplication());
        return entity;
    }

    @Override
    public List<BorrowForm> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest) {
                throw new Exception("param filterRequest null");
            }
            BorrowForm dto = this.whereDto(filterRequest);
            if (null == dto) {
                throw new Exception("whereDto null");
            }
            filterRequest.setTotalRecords(this.countByAnd(dto));
            String selectClause = "FROM TBorrowForm o ";
            String orderByClause = formatOrderBy(filterRequest.getOrderBy().toString());
            List<TBorrowForm> entities = this.findEntitiesByAnd(dto, selectClause, orderByClause, filterRequest.getDisplayLength(), filterRequest.getDisplayStart());
            return entities.stream().map(x -> service.dtoFromEntity(x)).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("filterBy", ex);
            throw ex;
        }
    }

    @Override
    public int countByAnd(BorrowForm dto) throws Exception {
        try {
            if (null == dto){
                throw new Exception("param dto null");
            }
            int count;
            String sql = "SELECT COUNT(o) FROM TBorrowForm o ";
            String whereClause = this.getWhereClause(dto, false);
            HashMap<String, Object> parameters = (HashMap<String, Object>) this.getParameters(dto);
            if (StringUtils.isNotEmpty(whereClause) && null != parameters && parameters.size() > 0) {
                count = borrowFormDao.count(sql.concat(whereClause), parameters);
            } else {
                count = borrowFormDao.count(sql);
            }
            return count;
        } catch (Exception ex) {
            log.error("countByAnd", ex);
            throw ex;
        }
    }

    @Override
    public List<TBorrowForm> findEntitiesByAnd(BorrowForm dto, String selectClause, String orderByClause, int limit, int offset) throws Exception {
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
            List<TBorrowForm> entities = borrowFormDao.getByQuery(hqlQuery, parameters, limit, offset);
            for (TBorrowForm entity : entities) {
                this.initEntity(entity);
            }
            return entities;
        } catch (Exception ex) {
            log.error("findEntitiesByAnd", ex);
            throw ex;
        }
    }

    @Override
    protected BorrowForm whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest) {
                throw new Exception("param filterRequest null");
            }
            BorrowForm dto = new BorrowForm();
            Application application = new Application();
            AppUser appUser = new AppUser();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent()){
                    continue;
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("application.appID")) {
                    application.setAppID(opValue.get());
                }
                if (entityWhere.getAttribute().equalsIgnoreCase("borCustomer.username")) {
                    appUser.setUsername(opValue.get());
                }
            }
            dto.setApplication(application);
            dto.setBorCustomer(appUser);
            return dto;
        } catch (Exception ex) {
            log.error("whereDto", ex);
            throw ex;
        }
    }

    @Override
    protected String getWhereClause(BorrowForm dto, boolean wherePrinted) throws Exception {
        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }
            StringBuilder searchStatement = new StringBuilder();
            searchStatement.append(getOperator(wherePrinted)).append(" o.TApplication.appRecStatus='A' AND o.TApplication.TMstApplicationType.aptCode = 'BOR' ");
            wherePrinted = true;

            Optional<Application> opPediApps = Optional.ofNullable(dto.getApplication());
            if (opPediApps.isPresent()) {
                Application pediApp = opPediApps.get();
                if (StringUtils.isNotBlank(pediApp.getAppID())){
                    searchStatement.append(
                            getOperator(wherePrinted) + "o.TApplication.appID LIKE :appID ");
                }
                Optional<AppUser> appUser = Optional.ofNullable(dto.getBorCustomer());
                if(appUser.isPresent()){
                    AppUser user = appUser.get();
                    if (StringUtils.isNotBlank(user.getUsername())){
                        searchStatement.append(
                                getOperator(wherePrinted) + "o.borCustomer.username LIKE :username ");
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
    protected Map<String, Object> getParameters(BorrowForm dto) throws Exception {
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
            Optional<AppUser> appUser = Optional.ofNullable(dto.getBorCustomer());
            if (appUser.isPresent()) {
                AppUser user = appUser.get();
                if (!StringUtils.isEmpty(user.getUsername())) {
                    parameters.put("username", "%" + user.getUsername() + "%");
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
