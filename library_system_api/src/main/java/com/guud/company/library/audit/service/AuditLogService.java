package com.guud.company.library.audit.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.audit.dto.AuditLog;
import com.guud.company.library.audit.model.TAuditLog;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.enums.ApplicationTypeEnum;
import com.guud.company.library.utils.PediUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service("auditLogService")
public class AuditLogService extends AbstractEntityService<TAuditLog, String, AuditLog> {
    private static final String auditTag = "T TAuditLog";
    private static final String tableName = "t_audit_Log";
    protected static Logger log = Logger.getLogger(AuditLogService.class);

    public AuditLogService() {
        super("auditLogDao", auditTag, TAuditLog.class.getName(), tableName);
    }

    @Override
    protected TAuditLog initEnity(TAuditLog entity) throws Exception {
        return entity;
    }

    @Override
    protected TAuditLog entityFromDTO(AuditLog dto) throws Exception {
        log.debug("AuditLogService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TAuditLog entity = new TAuditLog();
            entity = dto.toEntity(entity);

            return entity;
        } catch (Exception ex) {
            log.error("AuditLogService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected AuditLog dtoFromEntity(TAuditLog entity) throws Exception {
        log.debug("AuditLogService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            AuditLog dto = new AuditLog(entity);
            return dto;
        } catch (Exception ex) {
            log.error("AuditLogService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(AuditLog dto) throws Exception {
        log.debug("AuditLogService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getAudtId();
        } catch (Exception ex) {
            log.error("AuditLogService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TAuditLog updateEntity(ACTION attribute, TAuditLog entity, AppUser principal, Date date) throws Exception {
        log.debug("AuditLogService.updateEntity");

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
                    entity.setAudtId(PediUtility.generateId(ApplicationTypeEnum.CUS.name()));
                    entity.setAudtTimestamp(date);
                    break;

                case MODIFY:
                    entity.setAudtUid(opUserId.orElse("SYS"));
                    entity.setAudtTimestamp(date);
                    break;

                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("AuditLogService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TAuditLog updateEntityStatus(TAuditLog entity, char status) throws Exception {
        log.debug("AuditLogService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }
            return entity;
        } catch (Exception ex) {
            log.error("AuditLogService.updateEntityStatus", ex);
            throw ex;
        }

    }

    @Override
    protected AuditLog preSaveUpdateDTO(TAuditLog storedEntity, AuditLog dto) throws Exception {
        log.debug("AuditLogService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setAudtUid(storedEntity.getAudtUid());
            dto.setAudtAccnid(storedEntity.getAudtAccnid());

            return dto;
        } catch (Exception ex) {
            log.error("AuditLogService.preSaveUpdateDTO", ex);
            throw ex;
        }

    }

    @Override
    protected void preSaveValidation(AuditLog var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(AuditLog dto, boolean wherePrinted) throws Exception {
        log.debug("AuditLogService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getAudtId())) {
                searchStatement.append(getOperator(wherePrinted) + "o.audtId LIKE :audtId");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("AuditLogService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(AuditLog dto) throws Exception {
        log.debug("AuditLogService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(dto.getAudtId())) {
                parameters.put("audtId", "%" + dto.getAudtId() + "%");
            }
            return parameters;
        } catch (Exception ex) {
            log.error("AuditLogService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected AuditLog whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            AuditLog dto = new AuditLog();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent()) {
                    continue;
                }

                if (entityWhere.getAttribute().equalsIgnoreCase("audtId")) {
                    dto.setAudtUid(opValue.get());
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
    public AuditLog findById(String id) throws Exception {
        log.debug("AuditLogService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }
            TAuditLog entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);
            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("AuditLogService.findById", ex);
            throw ex;
        }
    }

    @Override
    public AuditLog deleteById(String id, AppUser principal) throws Exception {
        log.debug("AuditLogService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TAuditLog entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            AuditLog dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("AuditLogService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<AuditLog> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            AuditLog dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TAuditLog o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TAuditLog> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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

    @Override
    public AuditLog findML(AuditLog dto) throws Exception {
        return null;
    }
}
