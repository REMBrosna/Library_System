package com.guud.company.library.master.district.service;

import com.guud.company.library.administrator.domain.AppUser;
import com.guud.company.library.core.AbstractEntityService;
import com.guud.company.library.core.dto.EntityOrderBy;
import com.guud.company.library.core.dto.EntityWhere;
import com.guud.company.library.core.payload.EntityFilterRequest;
import com.guud.company.library.core.payload.EntityFilterResponse;
import com.guud.company.library.master.district.dto.District;
import com.guud.company.library.master.district.model.TMstDistrict;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistrictService extends AbstractEntityService<TMstDistrict, String, District> {

    private static final String auditTag = "T MST PROVINCE";
    private static final String tableName = "t_mst_district";
    protected static Logger log = Logger.getLogger(DistrictService.class);

    public DistrictService() {
        super("DistrictDao", auditTag, TMstDistrict.class.getName(), tableName);
    }

    @Override
    protected TMstDistrict initEnity(TMstDistrict entity) throws Exception {
        return entity;
    }

    @Override
    protected TMstDistrict entityFromDTO(District dto) throws Exception {
        log.debug("DistrictService.entityFromDTO");
        try {
            if (null == dto) {
                throw new Exception("dto is null");
            }

            TMstDistrict entity = new TMstDistrict();
            entity = dto.toEntity(entity);

            return entity;
        } catch (Exception ex) {
            log.error("DistrictService.entityFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected District dtoFromEntity(TMstDistrict entity) throws Exception {
        log.debug("DistrictService.dtoFromEntity");
        try {
            if (null == entity) {
                throw new Exception("param entity null");
            }
            District dto = new District(entity);
            return dto;
        } catch (Exception ex) {
            log.error("DistrictService.dtoFromEntity", ex);
            throw ex;
        }
    }

    @Override
    protected String entityKeyFromDTO(District dto) throws Exception {
        log.debug("DistrictService.entityKeyFromDTO");

        try {
            if (null == dto) {
                throw new Exception("dto param null");
            }

            return dto.getCode();
        } catch (Exception ex) {
            log.error("DistrictService.entityKeyFromDTO", ex);
            throw ex;
        }
    }

    @Override
    protected TMstDistrict updateEntity(ACTION attribute, TMstDistrict entity, AppUser principal, Date date) throws Exception {
        log.debug("DistrictService.updateEntity");

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
                    entity.setDisUidCreate(opUserId.orElse("SYS"));
                    entity.setDisDtCreate(date);
                    entity.setDisUidLupd(opUserId.orElse("SYS"));
                    entity.setDisDtLupd(date);
                    break;

                case MODIFY:
                    entity.setDisUidLupd(opUserId.orElse("SYS"));
                    entity.setDisDtLupd(date);
                    break;
                default:
                    break;
            }

            return entity;
        } catch (Exception ex) {
            log.error("DistrictService.updateEntity", ex);
            throw ex;
        }
    }

    @Override
    protected TMstDistrict updateEntityStatus(TMstDistrict entity, char status) throws Exception {
        log.debug("DistrictService.updateEntityStatus");

        try {
            if (null == entity) {
                throw new Exception("entity param is null");
            }

            entity.setRecStatus(status);
            return entity;
        } catch (Exception ex) {
            log.error("DistrictService.updateEntityStatus", ex);
            throw ex;
        }
    }

    @Override
    protected District preSaveUpdateDTO(TMstDistrict storedEntity, District dto) throws Exception {
        log.debug("DistrictService.preSaveUpdateDTO");

        try {
            if (null == storedEntity) {
                throw new Exception("param storedEntity is null");
            }
            if (null == dto) {
                throw new Exception("param dto is null");
            }

            dto.setDisUidCreate(storedEntity.getDisUidCreate());
            dto.setDisDtCreate(storedEntity.getDisDtCreate());

            return dto;
        } catch (Exception ex) {
            log.error("DistrictService.preSaveUpdateDTO", ex);
            throw ex;
        }
    }

    @Override
    protected void preSaveValidation(District var1, AppUser var2) throws Exception {
        // No implementation
    }

    @Override
    protected String getWhereClause(District dto, boolean wherePrinted) throws Exception {
        log.debug("DistrictService.getWhereClause");

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
            log.error("DistrictService.getWhereClause", ex);
            throw ex;
        }
    }

    @Override
    protected HashMap<String, Object> getParameters(District dto) throws Exception {
        log.debug("DistrictService.getParameters");

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
            log.error("DistrictService.getParameters", ex);
            throw ex;
        }
    }

    @Override
    protected District whereDto(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            District dto = new District();
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
    public District findById(String id) throws Exception {
        log.debug("DistrictService.findById");

        try {
            if (StringUtils.isEmpty(id)) {
                throw new Exception("param id is null or empty");
            }

            TMstDistrict entity = dao.find(id);
            if (null == entity) {
                throw new Exception("id: " + id);
            }
            this.initEnity(entity);

            return this.dtoFromEntity(entity);
        } catch (Exception ex) {
            log.error("DistrictService.findById", ex);
            throw ex;
        }
    }

    @Override
    public District deleteById(String id, AppUser principal) throws Exception {
        log.debug("DistrictService.deleteById");

        Date now = Calendar.getInstance().getTime();
        try {
            if (StringUtils.isEmpty(id))
                throw new Exception("param id null or empty");
            if (null == principal)
                throw new Exception("param prinicipal null");

            TMstDistrict entity = dao.find(id);
            if (null == entity)
                throw new Exception("id: " + id);
            this.initEnity(entity);

            this.updateEntityStatus(entity, 'I');
            this.updateEntity(ACTION.MODIFY, entity, principal, now);

            District dto = dtoFromEntity(entity);
            this.delete(dto, principal);
            return dto;
        } catch (Exception ex) {
            log.error("DistrictService.deleteById", ex);
            throw ex;
        }
    }

    @Override
    public List<District> filterBy(EntityFilterRequest filterRequest) throws Exception {
        try {
            if (null == filterRequest)
                throw new Exception("param filterRequest null");

            District dto = this.whereDto(filterRequest);
            if (null == dto)
                throw new Exception("whereDto null");

            filterRequest.setTotalRecords(super.countByAnd(dto));

            String selectClause = "from TMstDistrict o ";
            String orderByClause = filterRequest.getOrderBy().toString();
            List<TMstDistrict> entities = super.findEntitiesByAnd(dto, selectClause, orderByClause,
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

    public Optional<Object> getDistrictList(Map<String, String> params) throws Exception {
        log.debug("getDistrictList");
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

            List<District> en = this.filterBy(filterRequest);

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
    public District findML(District dto) throws Exception {
        return null;
    }
}
