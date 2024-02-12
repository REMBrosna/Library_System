package com.guud.company.library.application.common;

import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.payload.EntityFilterRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public abstract class AbstractApplication <D, E, S> implements IApplication<D, E>{

    // Static Attributes
    ////////////////////
    private static final Logger log = Logger.getLogger(AbstractApplication.class);

    @Autowired
    protected S service;

    // Constructors
    ///////////////
    public AbstractApplication() {
    }
    // Override Methods
    //////////////////

    @SuppressWarnings("unchecked")
    public D createAppObj(Object object) throws Exception {
        return createApp((D) object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object updateAppObj(Object object)
            throws Exception {
        return updateApp((D) object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public D deleteObj(Object object)
            throws Exception {
        return delete((D) object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object confirmAppObj(Object object)
            throws Exception {
        return confirmApp((D) object);
    }

    @Override
    public D getApp(String key) throws Exception {

        log.debug("getApp");
        try {

           return getAppForm(key);

        } catch (Exception ex) {
            log.error("getApp", ex);
            throw ex;
        }
    }

    protected abstract E initEntity(E entity) throws Exception;

    protected abstract D whereDto(EntityFilterRequest filterRequest) throws Exception;

    protected abstract String getWhereClause(D dto, boolean wherePrinted) throws Exception;

    protected abstract Map<String, Object> getParameters(D dto) throws Exception;

    protected abstract D getAppForm(String key) throws Exception;

    protected abstract String formatOrderBy(String attribute) throws Exception;

    protected EntityOrderBy formatOrderByObj(EntityOrderBy orderBy) throws Exception {

        if (orderBy == null)
            return null;

        if (StringUtils.isEmpty(orderBy.getAttribute()))
            return null;

        String newAttr = formatOrderBy(orderBy.getAttribute());
        if (StringUtils.isEmpty(newAttr))
            return orderBy;

        orderBy.setAttribute(newAttr);
        return orderBy;

    }

    protected String getOperator(boolean wherePrinted) {
        return wherePrinted ? " AND " : " WHERE ";
    }

    public S getService() {
        return service;
    }

    public void setService(S service) {
        this.service = service;
    }
}
