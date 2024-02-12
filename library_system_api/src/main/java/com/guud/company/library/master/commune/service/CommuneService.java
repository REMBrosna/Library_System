package com.guud.company.library.master.commune.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.master.commune.dto.Commune;
import com.guud.company.library.master.commune.model.TMstCommune;
import com.guud.company.library.master.province.model.TMstProvince;
import com.guud.company.library.master.province.service.ProvinceService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommuneService extends AbstractEntityService<TMstCommune, String, Commune> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_mst_commune";
    protected static Logger log = Logger.getLogger(CommuneService.class);

    public CommuneService() {
        super("CommuneDao", auditTag, TMstCommune.class.getName(), tableName);
    }

    @Override
    protected TMstCommune initEnity(TMstCommune entity) throws Exception {
        return entity;
    }

    @Override
    protected TMstCommune entityFromDTO(Commune dto) throws Exception {
        log.debug("CommuneService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TMstCommune entity = new TMstCommune();
            entity = dto.toEntity(entity);

            return entity;
        } catch (Exception ex) {
            log.error("CommuneService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected Commune dtoFromEntity(TMstCommune entity) throws Exception {
        log.debug("CommuneService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            Commune dto = new Commune(entity);
            return dto;
        } catch (Exception ex) {
            log.error("CommuneService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(Commune dto) throws Exception {
        log.debug("CommuneService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getCode();
        } catch (Exception ex) {
            log.error("CommuneService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TMstCommune updateEntity(ACTION attribute, TMstCommune entity, AppUser principal, Date date) throws Exception {
        log.debug("CommuneService.updateEntity");

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
                    entity.setComUidCreate(opUserId.orElse("SYS"));
                    entity.setComDtCreate(date);
                    entity.setComUidLupd(opUserId.orElse("SYS"));
                    entity.setComDtLupd(date);
                    break;

                case MODIFY:
                    entity.setComUidLupd(opUserId.orElse("SYS"));
                    entity.setComDtLupd(date);
                    break;
                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("CommuneService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TMstCommune updateEntityStatus(TMstCommune entity, char status) throws Exception {
        log.debug("CommuneService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("CommuneService.updateEntityStatus", ex);
            throw ex;
        }
    }

    @Override
    protected Commune preSaveUpdateDTO(TMstCommune storedEntity, Commune dto) throws Exception {
        log.debug("CommuneService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setComUidCreate(storedEntity.getComUidCreate());
            dto.setComDtCreate(storedEntity.getComDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("CommuneService.preSaveUpdateDTO", ex);
            throw ex;
        }
    }

    @Override
    protected void preSaveValidation(Commune var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(Commune dto, boolean wherePrinted) throws Exception {
        log.debug("CommuneService.getWhereClause");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            StringBuffer searchStatement = new StringBuffer();
            if (!StringUtils.isEmpty(dto.getCode())) {
                searchStatement.append(getOperator(wherePrinted)).append("o.code LIKE :code");
                wherePrinted = true;
            }
            return searchStatement.toString();
        } catch (Exception ex) {
            log.error("CommuneService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(Commune dto) throws Exception {
        log.debug("CommuneService.getParameters");

        try {
            if (null == dto) {
                throw new Exception("param dto null");
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(dto.getCode())) {
                parameters.put("code", "%" + dto.getCode() + "%");
            }

            return parameters;
        } catch (Exception ex) {
            log.error("CommuneService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected Commune whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Commune dto = new Commune();
            for (EntityWhere entityWhere : filterRequest.getWhereList()) {
                Optional<String> opValue = Optional.ofNullable(entityWhere.getValue());
                if (!opValue.isPresent())
                    continue;

                if (entityWhere.getAttribute().equalsIgnoreCase("code")) {
                    dto.setCode(opValue.get());
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
    public Commune findById(String id) throws Exception {
        log.debug("CommuneService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TMstCommune entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("CommuneService.findById", ex);
            throw ex;
        }
    }

    @Override
    public Commune deleteById(String id, AppUser principal) throws Exception {
        log.debug("CommuneService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TMstCommune entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            Commune dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("CommuneService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<Commune> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            Commune dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TMstCommune o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TMstCommune> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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

    public Optional<Object> getCommuneList(Map<String, String> params) throws Exception {
        log.debug("getCommuneList");
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

            List<Commune> en = this.filterBy(filterRequest);

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
    public Commune findML(Commune dto) throws Exception {
        return null;
    }
}
