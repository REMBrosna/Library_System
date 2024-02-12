package com.guud.company.library.core;

import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.administrator.domain.AppUser;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Log4j2
public abstract class AbstractEntityService <E, K, D> implements IEntityService<E, K, D>, IMultiLangEntityService<D>, ApplicationContextAware {

    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    protected HttpServletRequest request;
    protected GenericDao<E, K> dao;
    protected String daoName;
    protected String moduleName;
    protected String entityName;
    protected String tableName;

    public AbstractEntityService(String daoName, String moduleName, String entityName) {
        log.debug("AbstractEntityService");

        try {
            this.daoName = daoName;
            this.moduleName = moduleName;
            this.entityName = entityName;
        } catch (Exception var5) {
            log.error("AbstractEntityService", var5);
        }

    }

    public AbstractEntityService(String daoName, String moduleName, String entityName, String tableName) {
        log.debug("AbstractEntityService");

        try {
            this.daoName = daoName;
            this.moduleName = moduleName;
            this.entityName = entityName;
            this.tableName = tableName;
        } catch (Exception var6) {
            log.error("AbstractEntityService", var6);
        }

    }

    @PostConstruct
    protected void init() {
        log.debug("init");

        try {
            this.dao = (GenericDao)this.applicationContext.getBean(this.daoName);
        } catch (Exception var2) {
            log.error("init", var2);
        }

    }

    public void setApplicationContext(ApplicationContext context) {
        log.debug("setApplicationContext");

        try {
            this.applicationContext = context;
        } catch (Exception var3) {
            log.error("setApplicationContext", var3);
        }

    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    protected abstract E initEnity(E var1) throws Exception;

    protected abstract E entityFromDTO(D var1) throws Exception;

    protected abstract D dtoFromEntity(E var1) throws Exception;

    protected abstract K entityKeyFromDTO(D var1) throws Exception;

    protected abstract E updateEntity(ACTION var1, E var2, AppUser var3, Date var4) throws Exception;

    protected abstract E updateEntityStatus(E var1, char var2) throws Exception;

    protected abstract D preSaveUpdateDTO(E var1, D var2) throws Exception;

    protected abstract void preSaveValidation(D var1, AppUser var2) throws Exception;

    protected abstract String getWhereClause(D var1, boolean var2) throws Exception;

    protected abstract HashMap<String, Object> getParameters(D var1) throws Exception;

    protected abstract D whereDto(EntityFilterRequest var1) throws Exception;

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D find(D dto) throws Exception {
        log.debug("find");

        try {
            if (dto == null) {
                throw new Exception("dto param null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E entity = this.dao.find(key);
                    if (entity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.initEnity(entity);
                        dto = this.dtoFromEntity(entity);
                        if (dto == null) {
                            throw new Exception("dtoFromEntity");
                        } else {
                            return dto;
                        }
                    }
                }
            }
        } catch (Exception var4) {
            log.error("find", var4);
            throw var4;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public List<D> findAll() throws Exception {
        log.debug("findAll");
        boolean bThrowExceptionOnEmpty = false;

        try {
            List<E> entities = this.findAllEntity();
            if (bThrowExceptionOnEmpty && Collections.isEmpty(entities)) {
                throw new Exception("entities null");
            } else {
                log.debug("size: " + entities.size());
                List<D> dtos = new ArrayList();
                Iterator var5 = entities.iterator();

                while(var5.hasNext()) {
                    E entity = (E) var5.next();
                    this.initEnity(entity);
                    dtos.add(this.dtoFromEntity(entity));
                }

                return dtos;
            }
        } catch (Exception var6) {
            log.error("findAll", var6);
            throw var6;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public List<D> findByAnd(D dto, int iDisplayStart, int iDisplayLength, String selectClause, String orderByClause) throws Exception {
        log.debug("findByAnd");
        boolean bThrowExceptionOnEmpty = false;

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (StringUtils.isEmpty(selectClause)) {
                throw new Exception("param selectClause null or empty");
            } else {
                List<E> entities = this.findEntitiesByAnd(dto, selectClause, orderByClause, iDisplayLength, iDisplayStart);
                if (bThrowExceptionOnEmpty && Collections.isEmpty(entities)) {
                    throw new Exception("entities null or empty");
                } else {
                    List<D> dtos = new ArrayList();
                    Iterator var10 = entities.iterator();

                    while(var10.hasNext()) {
                        E entity = (E) var10.next();
                        this.initEnity(entity);
                        dtos.add(this.dtoFromEntity(entity));
                    }

                    return dtos;
                }
            }
        } catch (Exception var11) {
            log.error("findByAnd", var11);
            throw var11;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public int countByAnd(D dto) throws Exception {
        log.debug("countByAnd");

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else {
                String whereClause = this.getWhereClause(dto, false);
                HashMap<String, Object> parameters = this.getParameters(dto);
                int count;
                if (StringUtils.isNotEmpty(whereClause) && parameters != null && parameters.size() > 0) {
                    count = this.dao.count("SELECT COUNT(o) FROM " + this.entityName + " o" + whereClause, parameters);
                } else {
                    count = this.dao.count("SELECT COUNT(o) FROM " + this.entityName + " o");
                }

                return count;
            }
        } catch (Exception var5) {
            log.error("countByAnd", var5);
            throw var5;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D add(D dto, AppUser principal) throws Exception {
        log.debug("add");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                E entity = this.entityFromDTO(dto);
                if (entity == null) {
                    throw new Exception("entityFromDTO");
                } else {
                    K key = this.entityKeyFromDTO(dto);
                    Object dtoAdded;
                    if (key != null) {
                        dtoAdded = this.dao.find(key);
                        if (dtoAdded != null) {
                            throw new Exception("entity exist: " + key.toString());
                        }
                    }

                    entity = this.updateEntity(ACTION.CREATE, entity, principal, now);
                    this.dao.add(entity);
                    dtoAdded = this.dtoFromEntity(entity);
                    if (dtoAdded == null) {
                        throw new Exception("dtoFromEntity");
                    } else {
                        return (D) dtoAdded;
                    }
                }
            }
        } catch (Exception var7) {
            log.error("add", var7);
            throw var7;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public Object addObj(Object object, AppUser principal) throws Exception {
        log.debug("addObj");
        return this.add((D) object, principal);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D update(D dto, AppUser principal) throws Exception {
        log.debug("update");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E storedEntity = this.getDao().find(key);
                    if (storedEntity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.initEnity(storedEntity);
                        this.preSaveUpdateDTO(storedEntity, dto);
                        E entity = this.entityFromDTO(dto);
                        if (entity == null) {
                            throw new Exception("entityFromDTO");
                        } else {
                            entity = this.updateEntity(ACTION.MODIFY, entity, principal, now);
                            BeanUtils.copyProperties(entity, storedEntity);
                            this.dao.update(storedEntity);
                            return dto;
                        }
                    }
                }
            }
        } catch (Exception var7) {
            log.error("update", var7);
            throw var7;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D update(D dto, AppUser principal, boolean isAuditLog) throws Exception {
        log.debug("update");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E storedEntity = this.getDao().find(key);
                    if (storedEntity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.initEnity(storedEntity);
                        this.preSaveUpdateDTO(storedEntity, dto);
                        E entity = this.entityFromDTO(dto);
                        if (entity == null) {
                            throw new Exception("entityFromDTO");
                        } else {
                            entity = this.updateEntity(ACTION.MODIFY, entity, principal, now);
                            BeanUtils.copyProperties(entity, storedEntity);
                            this.dao.update(storedEntity);
                            return dto;
                        }
                    }
                }
            }
        } catch (Exception var8) {
            log.error("update", var8);
            throw var8;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public Object updateObj(Object object, AppUser principal) throws Exception {
        log.debug("updateObj");
        return this.update((D) object, principal);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public Object updateObjStatus(Object object, AppUser principal, char status) throws Exception {
        log.debug("updateObj");
        return this.updateStatus((D) object, principal, status);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D updateStatus(D dto, AppUser principal, char status) throws Exception {
        log.debug("activateDTO");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E storedEntity = this.getDao().find(key);
                    if (storedEntity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.initEnity(storedEntity);
                        if ("A".equalsIgnoreCase(String.valueOf(status)) || "I".equalsIgnoreCase(String.valueOf(status))) {
                            this.updateEntityStatus(storedEntity, status);
                            storedEntity = this.updateEntity(ACTION.MODIFY, storedEntity, principal, now);
                            this.dao.update(storedEntity);
                            dto = this.dtoFromEntity(storedEntity);
                            String auditStatus = "A".equalsIgnoreCase(String.valueOf(status)) ? ACTION.ACTIVATE.toString() : ACTION.DEACTIVATE.toString();
                        }

                        return dto;
                    }
                }
            }
        } catch (Exception var8) {
            log.error("update", var8);
            throw var8;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D delete(D dto, AppUser principal) throws Exception {
        log.debug("delete");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E storedEntity = this.getDao().find(key);
                    if (storedEntity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.updateEntityStatus(storedEntity, 'I');
                        this.updateEntity(ACTION.MODIFY, storedEntity, principal, now);
                        this.dao.update(storedEntity);
                        return dto;
                    }
                }
            }
        } catch (Exception var6) {
            log.error("delete", var6);
            throw var6;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D deleteObj(Object obj, AppUser principal) throws Exception {
        log.debug("deleteObj");
        return this.delete((D) obj, principal);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public boolean isRecordExists(D dto) throws Exception {
        log.debug("isRecordExists");

        try {
            if (dto == null) {
                throw new Exception("dto param null");
            } else {
                Boolean isKeyExist = Boolean.TRUE;
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    isKeyExist = Boolean.FALSE;
                } else {
                    E entity = this.dao.find(key);
                    if (entity == null) {
                        isKeyExist = Boolean.FALSE;
                    }
                }

                return isKeyExist;
            }
        } catch (Exception var5) {
            log.error("isRecordExists", var5);
            throw var5;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    public D addML(D dto, AppUser principal) throws Exception {
        log.debug("addML");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal null");
            } else {
                E entity = this.entityFromDTO(dto);
                if (entity == null) {
                    throw new Exception("entityFromDTO");
                } else {
                    entity = this.updateEntity(ACTION.CREATE, entity, principal, now);
                    this.dao.add(entity);
                    D dtoAdded = this.dtoFromEntity(entity);
                    if (dtoAdded == null) {
                        throw new Exception("dtoFromEntity");
                    } else {
                        K key = this.entityKeyFromDTO(dtoAdded);
                        if (key == null) {
                            throw new Exception("entityKeyFromDTO");
                        } else {
                            return dtoAdded;
                        }
                    }
                }
            }
        } catch (Exception var8) {
            log.error("addML", var8);
            throw var8;
        }
    }

    public D updateML(D dto, AppUser principal) throws Exception {
        log.debug("updateML");
        Date now = Calendar.getInstance().getTime();

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (principal == null) {
                throw new Exception("param principal  null");
            } else {
                K key = this.entityKeyFromDTO(dto);
                if (key == null) {
                    throw new Exception("entityKeyFromDTO");
                } else {
                    E storedEntity = this.getDao().find(key);
                    if (storedEntity == null) {
                        throw new Exception("key: " + key.toString());
                    } else {
                        this.initEnity(storedEntity);
                        this.preSaveUpdateDTO(storedEntity, dto);
                        E entity = this.entityFromDTO(dto);
                        if (entity == null) {
                            throw new Exception("entityFromDTO");
                        } else {
                            this.updateEntity(ACTION.MODIFY, entity, principal, now);
                            BeanUtils.copyProperties(entity, storedEntity);
                            this.dao.update(storedEntity);
                            return dto;
                        }
                    }
                }
            }
        } catch (Exception var8) {
            log.error("updateML", var8);
            throw var8;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    protected List<E> findAllEntity() throws Exception {
        log.debug("findAllEntity");

        try {
            List<E> entities = this.dao.getAll();
            Iterator var3 = entities.iterator();

            while(var3.hasNext()) {
                E entity = (E) var3.next();
                this.initEnity(entity);
            }

            return entities;
        } catch (Exception var4) {
            log.error("findAllEntity", var4);
            throw new Exception(var4);
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    protected List<E> findEntitiesByAnd(D dto, String selectClause, String orderByClause, int limit, int offset) throws Exception {
        log.debug("findEntitesByAnd");

        try {
            if (dto == null) {
                throw new Exception("param dto null");
            } else if (StringUtils.isEmpty(selectClause)) {
                throw new Exception("param selectClause null or empty");
            } else if (StringUtils.isEmpty(orderByClause)) {
                throw new Exception("param orderByClause null or empty");
            } else {
                String whereClause = this.getWhereClause(dto, false);
                log.debug("whereClause: " + whereClause);
                HashMap<String, Object> parameters = this.getParameters(dto);
                String hqlQuery = StringUtils.isEmpty(whereClause) ? selectClause + orderByClause : selectClause + whereClause + orderByClause;
                List<E> entities = this.dao.getByQuery(hqlQuery, parameters, limit, offset);
                Iterator var11 = entities.iterator();

                while(var11.hasNext()) {
                    E entity = (E) var11.next();
                    this.initEnity(entity);
                }

                return entities;
            }
        } catch (Exception var12) {
            log.error("findEntitiesByAnd", var12);
            throw var12;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = {Exception.class}
    )
    protected E findEntityByKey(K key) throws Exception {
        log.debug("findEntityByKey");

        try {
            if (key == null) {
                throw new Exception("param key null");
            } else {
                E entity = this.dao.find(key);
                if (entity == null) {
                    throw new Exception("key: " + key.toString());
                } else {
                    this.initEnity(entity);
                    return entity;
                }
            }
        } catch (Exception var3) {
            log.error("findEntityByKey", var3);
            throw var3;
        }
    }

    protected String getOperator(boolean whereprinted) {
        return whereprinted ? " AND " : " WHERE ";
    }

    public String getLocalAddress() {
        String ip = this.request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip)) {
            ip = this.request.getRemoteAddr();
        }

        return ip;
    }

    public GenericDao<E, K> getDao() {
        return this.dao;
    }

    public void setDao(GenericDao<E, K> dao) {
        this.dao = dao;
    }


    public static enum ACTION {
        CREATE,
        MODIFY,
        DELETE,
        ACTIVATE,
        DEACTIVATE;

        private ACTION() {
        }
    }
}
